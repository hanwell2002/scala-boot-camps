package com.newhopebootcamps.spark

import com.newhopebootcamps.spark.config.ApplicationConfiguration
import org.apache.spark.sql.SparkSession
//import org.slf4j.LoggerFactory

import org.apache.logging.log4j.scala.Logging
import org.apache.logging.log4j.Level

object SparkSessionStarter extends App with Logging {
  logger.info("@@@@@@@@@@@@@@@@@@ Loading project jar @@@@@@@@@@@@@@@@@@@@@@@")
  logger.info("@@@@@@@@@@@@@@@@@@ Loading project jar @@@@@@@@@@@@@@@@@@@@@@@")

  private val db_driver = ApplicationConfiguration.driver //"org.postgresql.Driver"
  private val db_url = ApplicationConfiguration.url //"jdbc:postgresql://localhost:5432/postgres"
  private val db_username = ApplicationConfiguration.username //postgres
  private val db_password = ApplicationConfiguration.password // password for postgres

  private val spark = SparkSession.builder()
    .master("local[1]")
    .appName("Simple ApplicationSimple Application")
///    .config("spark.jars", "/opt/bootcamps/lib/postgresql-42.5.2.jar")
    .getOrCreate();

  var query = "select * from Country where population < 15000"

  if (null != Class.forName(db_driver)) {
    logger.info("!! class driver loaded")
    val jdbcDF = spark.read
      .format("jdbc")
      .option("url", db_url)
      .option("user", db_username)
      .option("password", db_password)
      .option("dbtable", s"($query) as t")
      .load()
    jdbcDF.show()

    logger.info("Submitted job accomplished!")
  } else {
    logger.info(" class driver NOT loaded")
  }

  logger.debug("#################### Process Completed Successfully !!! ####################")

  spark.stop()
  logger.info(">> My Spark job stopped!")
}
