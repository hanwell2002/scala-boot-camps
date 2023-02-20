#!/bin/bash

PROGRAM=`basename $0`
USAGE="Usage: ${PROGRAM} [ -e server-name ] [ -f file-name] [ -v version] "
# ./deploy.sh -e localhost -f FortuneDist.tar -v 23.2.2
# ./deploy.sh -e localhost -f application-config-util -v 1.0.1

########################################################################
# get command line options
########################################################################
while getopts e:E:f:F:v:V: c
do
    case $c in
        e | E) SERVER=$OPTARG
        	echo "Server: ${SERVER}"
        	;;
        f | F) PKG_NAME=$OPTARG
        	echo "File name: ${PKG_NAME}"
        	;;
        v | V) VERSION=$OPTARG
        	echo "Version: ${VERSION}"
        	;;
        \?) echo "${USAGE}"
            exit 1;;
    esac
done

# DEPLOY_BASE_DIR=`pwd`
DEPLOY_BASE_DIR=/opt/bootcamps/release/deployments
# APP_NAME=`echo ${PKG_NAME}|cut -f1 -d'-'`
APP_NAME=`echo ${PKG_NAME}`
echo "APP_NAME =============${APP_NAME}"
## Set variables, RELEASE_VERSION must be set based on each release
RELEASE_VERSION="${APP_NAME}-${VERSION}"
# PKG_BASE_DIR=${DEPLOY_BASE_DIR}/$RELEASE_VERSION
PKG_BASE_DIR=${DEPLOY_BASE_DIR}

echo "DEPLOY_BASE_DIR: $DEPLOY_BASE_DIR"
echo "APP_NAME: $APP_NAME"
echo "RELEASE_VERSION: $RELEASE_VERSION"
echo "PKG_BASE_DIR: $PKG_BASE_DIR"

# cp -p ./target/*.jar $DEPLOY_BASE_DIR
cp ./target/universal/${APP_NAME}-${VERSION}.*  ${DEPLOY_BASE_DIR}

# check the tar file
if [[ ! -e "$DEPLOY_BASE_DIR/$PKG_NAME-${VERSION}.tgz" ]];
then
    echo "`date`: No tar file"
    exit 1
fi

## make sure the archive directory exists
if [ ! -d $DEPLOY_BASE_DIR/archive ];
then
    mkdir $DEPLOY_BASE_DIR/archive
fi

# If the same version exists, archive the existing package folder, then re-deploy it again
if [ -d $PKG_BASE_DIR ];
then
    cd ${DEPLOY_BASE_DIR}
    # tar -cvf ${DEPLOY_BASE_DIR}/archive/${RELEASE_VERSION}_`date +"%Y%m%d%H%M%S"`.tar ${DEPLOY_BASE_DIR}/${RELEASE_VERSION}
    tar -cvf ${DEPLOY_BASE_DIR}/archive/${RELEASE_VERSION}_`date +"%Y%m%d%H%M%S"`.tar ${DEPLOY_BASE_DIR}/${RELEASE_VERSION}
    \rm -r ${DEPLOY_BASE_DIR}/${RELEASE_VERSION}
    echo "`date`: archived ${RELEASE_VERSION}"
fi

mkdir ${PKG_BASE_DIR}

cd ${PKG_BASE_DIR}
(
    echo "Server: ${SERVER}"
    echo "File name: ${PKG_NAME}"
    echo "Version: ${VERSION}"

    ########################################################################
    # Uncompress the tar file
    ########################################################################
   # cd $DEPLOY_BASE_DIR

    CURR_DIR=`pwd`
    echo "Current dir: ${CURR_DIR}"
    echo "------------------------------------------------------"
    echo ">> PKG: ${DEPLOY_BASE_DIR}/${PKG_NAME}"
    echo "------------------------------------------------------"
    # tar -xvf ${DEPLOY_BASE_DIR}/${PKG_NAME}
    # tar -xvf ${DEPLOY_BASE_DIR}/${APP_NAME}.tar

    echo "package=name-->"${DEPLOY_BASE_DIR}/${APP_NAME}-${VERSION}.tgz
    tar -xvf ${DEPLOY_BASE_DIR}/${APP_NAME}-${VERSION}.tgz

    ########################################################################
    # deploy Util scipts if existed
    ########################################################################
    if [ -d deployUtil ]; then
        chmod +x deployUtil/*
        cp -f deployUtil/* ${DEPLOY_BASE_DIR}
    fi

    ########################################################################
    # Add execute permission on release script for exec
    ########################################################################
    RELEASE_VER=`echo ${VERSION}|cut -f1 -d'-'`
    #RELEASE_SCRIPT=${PKG_BASE_DIR}/release_${RELEASE_VER}.sh
    RELEASE_SCRIPT=${PKG_BASE_DIR}/$RELEASE_VERSION/release_${RELEASE_VER}.sh


    chmod u+x ${RELEASE_SCRIPT}
    ${RELEASE_SCRIPT} ${PKG_BASE_DIR}

) >> ${PKG_BASE_DIR}/release.log 2>&1

exit 0
