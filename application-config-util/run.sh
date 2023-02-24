#!/bin/bash

export APP_BASE=/opt/bootcamps
APP_JAR_NAME=application-config-util
VERSION=1.0.1
DEPLOY_BASE_DIR=/opt/bootcamps/release/deployments

chmod 754 ${APP_BASE}/script/*

${APP_BASE}/script/start.sh
