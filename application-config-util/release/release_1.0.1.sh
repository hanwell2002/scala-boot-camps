#!/bin/bash

PKG_BASE_DIR=$1

echo "PKG_BASE_DIR-------------->" $PKG_BASE_DIR

########################################################################
## Include the common deployment function
########################################################################
#. "${PKG_BASE_DIR}"/../deployCommonFunction.sh
. "${PKG_BASE_DIR}"/deployCommonFunction.sh
# . ${PKG_BASE_DIR}/../deployApplication.sh
. ${PKG_BASE_DIR}/deployApplication.sh

APP_BASE=/opt/bootcamps
########################################################################
### Specify directories to be created
########################################################################
# new_dir[1]=${APP_BASE}/ftp-in

########################################################################
### Specify files need to be deployed
########################################################################
from_files[1]=${PKG_BASE_DIR}/lib/application-config-util.application-config-util-1.0.1.jar
from_files[2]=${PKG_BASE_DIR}/config/application.env
from_files[3]=${PKG_BASE_DIR}/config/logback.xml
from_files[4]=${PKG_BASE_DIR}/config/database.cfg



#####################F###################################################
### Destination of deployed files, the sequence must be matched with from_files
########################################################################
to_files[1]=${APP_BASE}/lib/application-config-util.jar
to_files[2]=${APP_BASE}/config/application.env
to_files[3]=${APP_BASE}/config/clogback.xml
to_files[4]=${APP_BASE}/config/database.cfg

########################################################################

echo "`date`: Deploy starts"

# createDir

# chmod g+w ${APP_BASE}/log/application-config-util
# chmod g+w ${APP_BASE}/data/application-config-util

deploy

echo "Deployme is completed, please verify."
