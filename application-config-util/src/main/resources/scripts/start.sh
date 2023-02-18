#!/bin/bash

PROGRAM=`basename $0`

APP_BASE=/opt/bootcamps
APP_NAME=application
LOG_NAME=application
LOG_HOME=$APP_BASE/log

. ${APP_BASE}/config/application.env
CLASSPATH=$CLASSPATH:$SCALA_HOME/lib/scala-library.jar

# read Java Garbage collection doc.
GC_OPTS=\
"-Xloggc:$LOG_HOME/${LOG_NAME}_GC_`date '+%Y-%m-%d_%H%M%S'`.log. \
-XX:+PrintGCDateStamps \
-XX:+PrintGCDetails"

NOHUP_LOG_OPTS="$LOG_HOME/${LOG_NAME}_NoHup.log.`date '+%Y-%m-%d_%H%M%S'`"

VM_ARGS="-Xms512m -Xmx2048m"
APP_JVM_PARAMS="-Dlogback.configurationFile=${APP_BASE}/config/logback.xml -Dconfig.file=${APP_BASE}/config/application.conf -Dfile.ending=UTF8"
APP_JVM_PARAMS="${APP_JVM_PARAMS} ${VM_ARGS}"

echo "$PROGRAM is executing"
rm ${APP_BASE}/log/*

# nohup scala ${APP_JVM_PARAMS} ${GC_OPTS} ${APP_JVM_PARAMS} ${APP_BASE}/lib/${APP_JAR} > $NOHUP_LOG_OPTS 2>&1
${JAVA_HOME}/bin/java -cp ${CLASSPATH} ${GC_OPTS} ${APP_JVM_PARAMS} -jar ${APP_BASE}/lib/${APP_JAR} > $NOHUP_LOG_OPTS 2>&1

exitCode=$?
# ne , e, gt, lt, = ==
if [ $exitCode -ne 0 ]; then
   ALARM_MSG="APP_ERROR: Applicaton-Config-Util exit abnormally!"
   echo "$ALARM_MSG"
   # SENT EMAIL "PRIORITY_EXIT" "$ALARM_MSG"
   exit 1
fi


exit 0
echo
echo "==========================================="
echo $APP_NAME stopped at `date '+%d/%m/%Y %H:%M'` with exit code $exitCode
echo "==========================================="
echo