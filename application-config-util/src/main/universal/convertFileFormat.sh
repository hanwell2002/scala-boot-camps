#!/bin/bash

###APP_BASE=/opt/bootcamps/script

APP_BASE=$1

for i in "sh" "config" "xml" "csv" "cfg" "conf" "properties" "ini" "html" "jsp" "txt" "sql" "env"
do 
	echo "****************************************"
	echo "* Converting (dos2unix) files of type \"$i\""
	echo "****************************************"
	find $APP_BASE -name "*.$i" -exec echo '{}' \; -exec sh -c 'dos2unix -437 "$0" "$0"' {} \;	
done
