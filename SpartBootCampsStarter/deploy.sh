#!/bin/bash

# mvn package
cp -p target/*.jar /opt/bootcamps/lib

APP_BASE=/opt/bootcamps
JDBC_DRIVER_JAR="postgresql-42.5.2.jar"
log4j_setting="-Dlog4j2.configurationFile=file:/opt/bootcamps/config/log4j2.properties"

spark-submit --name "NewHopeBootCamps Spark Starter" \
--master local[2] \
--driver-class-path /opt/bootcamps/lib/${JDBC_DRIVER_JAR} \
--jars /opt/bootcamps/lib/${JDBC_DRIVER_JAR} \
--conf "spark.driver.extraJavaOptions=${log4j_setting}" \
--conf "spark.executor.extraJavaOptions=${log4j_setting}" \
--class com.newhopebootcamps.spark.PostgresDataFrameDemo \
--files ${APP_BASE}/config/log4j2.properties \
$APP_BASE/lib/SpartBootCampsStarter-1.0.1.jar


