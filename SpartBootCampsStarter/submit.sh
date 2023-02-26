#!/bin/bash

# mvn package
cp -p target/*.jar /opt/bootcamps/lib

# spark-submit \
# --class com.newhopebootcamps.spark.SparkSessionStarter \
# /opt/bootcamps/lib/SpartBootCampsStarter-1.0.1.jar

spark-submit --name "NewHopeBootCamps Spark Starter" \
--master local[4] \
--driver-class-path /opt/bootcamps/lib/postgresql-42.5.2.jar \
--jars /opt/bootcamps/lib/postgresql-42.5.2.jar \
--conf spark.eventLog.enabled=false \
--conf "spark.executor.extraJavaOptions=-XX:+PrintGCDetails -XX:+PrintGCTimeStamps" \
--class com.newhopebootcamps.spark.PostgresDataFrameDemo \
/opt/bootcamps/lib/SpartBootCampsStarter-1.0.1.jar
