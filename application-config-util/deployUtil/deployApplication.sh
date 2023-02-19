#!/bin/bash

########################################
## Define base directory for the application
########################################
APP_BASE=/opt/bootcamps

##########################################
## Get the current date for backup purpose
##########################################
SUFFIX=`date +%Y%m%d`

#############################################################
## Based the host name to deploy environment specific changes
#############################################################
HOSTNAME=`hostname`
	echo "Host: ${HOSTNAME}"
	
	if [[ $HOSTNAME = "ubuntu-vm" ]];then
		ENV="dev"
	elif [[ $HOSTNAME = "ubuntu-vm2" ]];then
		ENV="uat"
	elif [[ $HOSTNAME = "ubuntu-vm3" ]];then
		ENV="prod"
	elif [[ $HOSTNAME = "ubuntu-vm4" ]];then
		ENV="prod"
	else
		echo "Failed to identify the environment, exit the release"
		exit 1
	fi

	echo "Environment: ${ENV}"

########################################################################
# Convert files to Unix format
########################################################################
${PKG_BASE_DIR}/../convertFileFormat.sh ${PKG_BASE_DIR}
chmod 755 ${PKG_BASE_DIR}/*.sh
