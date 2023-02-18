#!/bin/bash

export APP_BASE=/opt/bootcamps

mkdir -p ${APP_BASE}/bin
mkdir -p ${APP_BASE}/script
mkdir -p ${APP_BASE}/lib
mkdir -p ${APP_BASE}/config
mkdir -p ${APP_BASE}/data
mkdir -p ${APP_BASE}/feeds
mkdir -p ${APP_BASE}/reports
mkdir -p ${APP_BASE}/log
mkdir -p ${APP_BASE}/release/deployments

chmod 755 ${APP_BASE}/bin
chmod 755 ${APP_BASE}/script
chmod 755 ${APP_BASE}/lib
chmod 755 ${APP_BASE}/config
chmod 755 ${APP_BASE}/data
chmod 755 ${APP_BASE}/feeds
chmod 755 ${APP_BASE}/reports
chmod 755 ${APP_BASE}/log
chmod 755 ${APP_BASE}/release
chmod 755 ${APP_BASE}/release/deployments

