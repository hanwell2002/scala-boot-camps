#!/bin/bash

SCRIPT_PERMISSION=754
WAR_PERMISSION=644
JAR_PERMISSION=644
DEFAULT_PERMISSION=644

createDir()
{
	echo ""
	echo "##### Create Directory begins #########################"

	if [[ -n ${new_dir[@]} ]];
	then
		idx=1
		n_create=0
		n_fail=0
		for i in ${new_dir[@]}
		do
	                echo ""
		        echo "Directory to create: ${idx}"
			echo "Directory path is: $i"
			if [[ ! -d $i ]];
			then
				(( n_create=n_create+1 ))
				mkdir -p $i
				chmod 755 $i
				echo "Try to create directory and set 755"
			        if [[ ! -d $i ]];
				then
					echo "ERROR: Failed to create the directory"
					(( n_fail=n_fail+1 ))
				else
					echo "ROLLBACK: rm -r $i"
					echo "Succeeded to create the directory"
				fi
			else
				echo "No need to create the directory, which has already existed"
			fi
			(( idx=idx+1 ))
		done

		echo ""
	        echo "Summary:"
	        echo "Directories to list: ${#new_dir[@]}"
	        echo "Directories to create: ${n_create}"
	        echo "Directories to fail: ${n_fail}"
	else
	        echo ""
		echo "No new directories need to create"
	fi

	echo ""
	echo "##### Create Directory ends ###########################"
	echo ""
}

validate()
{
	echo ""
	echo "..... Validate files begins ..........................."

	i=1

	## restart instances
	for file in ${from_files[@]}
	do
	        echo ""
		echo "Validate file $file "

		if [[ ! -e $file ]];
		then
			echo "File $file to be deployed does not exist."
			exit 1
		fi

		dest_file=${to_files[i]}

		echo "dest file $dest_file"

		if [[ -z $dest_file ]];
		then
			echo "Deploy destination for the file $file is not set up."
			exit 1
		fi

		((i=$i+1))
	done

	echo ""
	echo "..... Validate files ends ............................."
	echo ""
}

backup()
{
	echo ""
	echo ">>>>> Backup files begins >>>>>>>>>>>>>>>>>>>>>>>>>>>>>"

	for file in ${to_files[@]}
	do
	        echo ""

		if [[ ! -e $file ]];
		then
			echo "The $file does not exist. No need to backup"
			continue
		fi

		if [[ -e $file.$SUFFIX ]];
		then
			echo "Backup file already exists. No need of backup"
		else
			echo "Backup file $file to $file.$SUFFIX"
			cp -p $file $file.$SUFFIX
		fi
	done

	echo ""
	echo ">>>>> Backup files ends >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
	echo ""
}

deploy_folders()
{
	echo ""
	echo "***** Deploy folders begins ***************************"
	echo ""

	if [[ ${#from_folders[@]} == ${#to_folders[@]} ]];
	then
		echo "Equal number of from_folders ${#from_folders[@]} and to_folders ${#to_folders[@]}"
	else
		echo "ERROR: Wrong number of from_folders ${#from_folders[@]} and to_folders ${#to_folders[@]}"
		exit 1
	fi

	index_folder=1
	for folder in ${from_folders[@]}
	do
		echo ""
		echo "Folder pair: ${index_folder} ****************************************"
		echo ""

		# validate the soruce folder

		src_folder=$folder

		echo "Validate source folder: $src_folder"

		if [[ ${src_folder#${src_folder%?}} = "/" ]];
		then
			src_folder=${src_folder%?}
		        echo "Source folder no slash: ${src_folder}"
		fi

		if [[ ! -d $src_folder ]];
		then
			echo "ERROR: Folder $src_folder to be deployed does not exist."
			exit 1
		fi

		echo "Validate result:        [PASSED]"
		echo ""

		# validate the dest folder

		dest_folder=${to_folders[index_folder]}

		echo "Validate dest folder:   $dest_folder"

		if [[ ${dest_folder#${dest_folder%?}} = "/" ]];
		then
			dest_folder=${dest_folder%?}
		        echo "Dest folder no slash:   ${dest_folder}"
		fi

		if [[ -z $dest_folder ]];
		then
			echo "ERROR: Deploy destination for the folder $src_folder is not set up."
			exit 1
		fi

		if [[ ! -d $dest_folder ]];
		then
			echo "ERROR: The dest folder doesn't exist"
			exit 1
		fi

		echo "Validate result:        [PASSED]"
		echo ""

		# extract files from the source folder and set to the dest folder

                set -A from_files_for_folder
                set -A to_files_for_folder

		findString="find ${src_folder}/* -prune -type f"
		array_of_files=`${findString} 2>/dev/null`

		if [[ ! $array_of_files = "" ]];
		then
		        index_file=1
			echo "${array_of_files}" | while read line
			do
				from_files_for_folder[index_file]=${line}
				to_files_for_folder[index_file]="${dest_folder}/`basename ${line}`"

				echo "from_files_for_folder[${index_file}] = ${from_files_for_folder[${index_file}]}"
				echo "to_files_for_folder[${index_file}]   = ${to_files_for_folder[${index_file}]}"

				((index_file=$index_file+1))
			done

                        deploy_files_for_folder
		else
			echo "No file to deploy:      ${src_folder}"
		fi

                set -A from_files_for_folder
                set -A to_files_for_folder

		((index_folder=$index_folder+1))
	done

	echo ""
	echo "***** Deploy folders ends *****************************"
	echo ""
}

deploy_files_for_folder()
{
	i=1
	n_deploy=0
	n_fail=0
	n_backup=0

	## restart instances
	for file in ${from_files_for_folder[@]}
	do
		echo ""
		echo "File pair: ${i}"

		echo "Validate source file: $file "

		if [[ ! -e $file ]];
		then
			echo "ERROR: File $file to be deployed does not exist."
			exit 1
		fi

		dest_file=${to_files_for_folder[i]}

		echo "Validate dest file:   $dest_file"

		if [[ -z $dest_file ]];
		then
			echo "ERROR: Deploy destination for the file $file is not set up."
			exit 1
		fi

		need_to_deploy=true

		if [[ -e $dest_file ]];
		then
			echo "The dest file has already existed"

			srcchecksum=`md5sum ${file} | awk '{print $1}'`
			destchecksum=`md5sum ${dest_file} | awk '{print $1}'`

			echo "The source file checksum: $srcchecksum"
			echo "The dest file checksum:   $destchecksum"

			if [[ "$srcchecksum" == "$destchecksum" ]];
			then
				echo "No need to deploy this file due to the same checksum"
				need_to_deploy=false
			else
				echo "The source and dest have the different checksum"
			fi
		else
			echo "The dest file doesn't exist"
		fi

		if $need_to_deploy;
		then
			echo "Will deploy this file"
			(( n_deploy=n_deploy+1 ))

			if [[ -e $dest_file ]];
			then
				if [[ -e $dest_file.$SUFFIX ]];
				then
					echo "Backup file already exists. No need of backup"
				else
					echo "Backup file $dest_file to $dest_file.$SUFFIX"
					cp -p $dest_file $dest_file.$SUFFIX
					(( n_backup=n_backup+1 ))
				fi

				if [ ! -w $dest_file ];
				then
					#change the file permission so it can copy
					chmod u+w $dest_file
				fi

			else
				echo "The $dest_file does not exist. No need to backup"
			fi

			echo "Begin to deploy file $file to $dest_file"

			#copy file
			cp $file $dest_file

			echo "Grant permission"
			case $dest_file in
				*.jar)
					echo "Jar file, granted with $JAR_PERMISSION"
					chmod $JAR_PERMISSION $dest_file
					;;
				*.sh)
					echo "Shell Script, granted with $SCRIPT_PERMISSION"
					chmod $SCRIPT_PERMISSION $dest_file
					;;
				*.war)
					echo "War file, granted with $WAR_PERMISSION"
					chmod $WAR_PERMISSION $dest_file
					;;
				*)
					echo "Other type file, granted with $DEFAULT_PERMISSION"
					chmod $DEFAULT_PERMISSION $dest_file
					;;
			esac

			echo "Do the post verification based on checksum"

			srcchecksum=`md5sum ${file} | awk '{print $1}'`
			destchecksum=`md5sum ${dest_file} | awk '{print $1}'`

			echo "The source file checksum: $srcchecksum"
			echo "The dest file checksum:   $destchecksum"

			if [[ "$srcchecksum" == "$destchecksum" ]];
			then
				echo "Verified file ${file}. File has been deployed"
			else
				echo "ERROR: File ${file} has not been deployed. Please check it out"
				(( n_fail=n_fail+1 ))
			fi
		fi

		((i=$i+1))
	done

	echo ""
	echo "Summary:"
	echo "Files to list: ${#from_files_for_folder[@]}"
	echo "Files to deploy: ${n_deploy}"
	echo "Files to backup: ${n_backup}"
	echo "Files to fail: ${n_fail}"
}

deploy_files()
{
	echo ""
	echo "***** Deploy files begins *****************************"

	i=1
	n_deploy=0
	n_fail=0
	n_backup=0

	## restart instances
	for file in ${from_files[@]}
	do
		echo ""
		echo "File pair: ${i}"

		echo "Validate source file: $file "

		if [[ ! -e $file ]];
		then
			echo "ERROR: File $file to be deployed does not exist."
			exit 1
		fi

		dest_file=${to_files[i]}

		echo "Validate dest file:   $dest_file"

		if [[ -z $dest_file ]];
		then
			echo "ERROR: Deploy destination for the file $file is not set up."
			exit 1
		fi

		need_to_deploy=true

		if [[ -e $dest_file ]];
		then
			echo "The dest file has already existed"

			srcchecksum=`md5sum ${file} | awk '{print $1}'`
			destchecksum=`md5sum ${dest_file} | awk '{print $1}'`

			echo "The source file checksum: $srcchecksum"
			echo "The dest file checksum:   $destchecksum"

			if [[ "$srcchecksum" == "$destchecksum" ]];
			then
				echo "No need to deploy this file due to the same checksum"
				need_to_deploy=false
			else
				echo "The source and dest have the different checksum"
			fi
		else
			echo "The dest file doesn't exist"
		fi

		if $need_to_deploy;
		then
			echo "Will deploy this file"
			(( n_deploy=n_deploy+1 ))

			if [[ -e $dest_file ]];
			then
				if [[ -e $dest_file.$SUFFIX ]];
				then
					echo "Backup file already exists. No need of backup"
				else
					echo "Backup file $dest_file to $dest_file.$SUFFIX"
					cp -p $dest_file $dest_file.$SUFFIX
					(( n_backup=n_backup+1 ))
				fi
				if [ ! -w $dest_file ];
                                then
					#change the file permission so it can copy
					chmod u+w $dest_file
				fi

				echo "ROLLBACK: cp -p $dest_file.$SUFFIX $dest_file"

			else
				echo "ROLLBACK: rm $dest_file"
				echo "The $dest_file does not exist. No need to backup"
			fi


			echo "Begin to deploy file $file to $dest_file"

			#copy file
			cp $file $dest_file

			echo "Grant permission"
			case $dest_file in
				*.ksh)

					echo "Shell Script KSH, granted with $SCRIPT_PERMISSION"
					chmod $SCRIPT_PERMISSION $dest_file
					;;
				*.sh)

					echo "Shell Script, granted with $SCRIPT_PERMISSION"
					chmod $SCRIPT_PERMISSION $dest_file
					;;
				*.war)
					echo "War file, granted with $WAR_PERMISSION"
					chmod $WAR_PERMISSION $dest_file
					;;
				*.jar)

					echo "Jar file, granted with $JAR_PERMISSION"
					chmod $JAR_PERMISSION $dest_file
					;;
				*)

					echo "Other type file, granted with $DEFAULT_PERMISSION"
					chmod $DEFAULT_PERMISSION $dest_file
					;;
			esac

			echo "Do the post verification based on checksum"

			srcchecksum=`md5sum ${file} | awk '{print $1}'`
			destchecksum=`md5sum ${dest_file} | awk '{print $1}'`

			echo "The source file checksum: $srcchecksum"
			echo "The dest file checksum:   $destchecksum"

			if [[ "$srcchecksum" == "$destchecksum" ]];
			then
				echo "Verified file ${file}. File has been deployed"
			else
				echo "ERROR: File ${file} has not been deployed. Please check it out"
				(( n_fail=n_fail+1 ))
			fi
		fi

		((i=$i+1))
	done

	echo ""
	echo "Summary:"
	echo "Files to list: ${#from_files[@]}"
	echo "Files to deploy: ${n_deploy}"
	echo "Files to backup: ${n_backup}"
	echo "Files to fail: ${n_fail}"

	echo ""
	echo "***** Deploy files ends *******************************"
	echo ""
}

deploy()
{
    deploy_folders
    deploy_files
}

validateTargetDir()
{
	echo ""
	echo "<<<<< Validate target directory begins <<<<<<<<<<<<<<<<"

	for file in ${to_files[@]}
	do
	        echo ""
		targetdir=`dirname $file`
		if [ -d "$targetdir" ]; then
			echo "validate target directory ${targetdir} => directory exists"
		else
			echo "validate target directory ${targetdir} => !!!!!!!!! directory dose not exists !!!!!!!!!!"
		fi
	done

	echo ""
	echo "<<<<< Validate target directory ends <<<<<<<<<<<<<<<<<<"
	echo ""
}

checkLog()
{
	errLog=`cat $1 | egrep "ERROR|Error|error"`
	if [[ ! -z $errLog ]];
	then
	        echo "$errLog"
		echo "exit 1"
		exit 1
	fi
}
