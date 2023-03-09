#!/bin/bash

# mvn package
cp -p target/scala-2.12/*.jar /opt/bootcamps/lib

spark-submit --name "Spark Starter Application" \
--master local[4] \
--driver-class-path /opt/bootcamps/lib/postgresql-42.5.2.jar \
--jars /opt/bootcamps/lib/postgresql-42.5.2.jar \
--conf spark.eventLog.enabled=false \
--conf "spark.executor.extraJavaOptions=-XX:+PrintGCDetails -XX:+PrintGCTimeStamps" \
--class com.newhopebootcamps.spark.PostgresDataFrameDemo \
/opt/bootcamps/lib/spark-starter-assembly-1.0.1.jar


#--conf "spark.driver.extraJavaOptions=-Dlog4j2.configurationFile=file:/opt/bootcamps/config/starter/log4j2.properties"
#--conf "spark.executor.extraJavaOptions=-Dlog4j2.configurationFile=file:/opt/bootcamps/config/starter/log4j2.properties"

# command line for Windows:
# copy target/scala-2.12/*.jar /opt/bootcamps/lib
# spark-submit --name "Spark Starter Application" --master local[4] --driver-class-path /opt/bootcamps/lib/postgresql-42.5.2.jar --jars /opt/bootcamps/lib/postgresql-42.5.2.jar --conf spark.eventLog.enabled=false --conf "spark.executor.extraJavaOptions=-XX:+PrintGCDetails -XX:+PrintGCTimeStamps" --class com.newhopebootcamps.spark.PostgresDataFrameDemo /opt/bootcamps/lib/spark-starter-assembly-1.0.1.jar
