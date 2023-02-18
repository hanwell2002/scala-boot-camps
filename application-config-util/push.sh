#!/bin/bash
# ./deploy.sh -e ${appserver} -f application.tar.gz -v 1.0.1

export APP_BASE=/opt/bootcamps
APP_JAR_NAME=application-config-util
VERSION=1.0.1
DEPLOY_BASE_DIR=/opt/bootcamps/release/deployments
# rm ${APP_BASE}/script/*
# rm ${APP_BASE}/lib/*

# cp ./target/universal/${APP_JAR_NAME}-${VERSION}.*  ${DEPLOY_BASE_DIR}
cp ./target/scala-2.13/${APP_JAR_NAME}-assembly-${VERSION}.*  ${APP_BASE}/lib/

cp ./target/scala-2.13/application-config-util* ${APP_BASE}/lib/
cp ./src/main/resources/scripts/*.sh ${APP_BASE}/script
cp ./src/main/resources/logback.xml ${APP_BASE}/config
cp ./src/main/resources/config/application.env ${APP_BASE}/config
cp ./src/main/resources/database.cfg ${APP_BASE}/config



