package com.newhopebootcamps.spark

import com.newhopebootcamps.spark.config.ApplicationConfiguration
import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory

object SparkSessionStarter extends App {
  private val logger = LoggerFactory.getLogger("SparkSessionStarter.class")
  logger.info("@@@@@@@@@@@@@@@@@@ Loading project jar @@@@@@@@@@@@@@@@@@@@@@@")
  println("@@@@@@@@@@@@@@@@@@ Loading project jar @@@@@@@@@@@@@@@@@@@@@@@")

  private val csvFile = "/opt/bootcamps/out/tb_name.csv" // Should be some file on your system
  private val jsonFile = "/opt/bootcamps/output/tb_json.json" // Should be some file on your system
  private val conf_loc = "/home/arceed/fortune/config/spark/fortune100.cfg"

  private val db_driver = ApplicationConfiguration.driver //"org.postgresql.Driver"
  private val db_url = ApplicationConfiguration.url //"jdbc:postgresql://localhost:5432/postgres"
  private val db_username = ApplicationConfiguration.username //postgres
  private val db_password = ApplicationConfiguration.password // password for postgres

  private val spark = SparkSession.builder()
    .master("local[1]")
    .appName("Simple ApplicationSimple Application")
    .config("spark.jars", "/opt/bootcamps/lib/postgresql-42.5.2.jar")
    .getOrCreate();

  // Tell spark which driver to use
  var query = "select * from Country where population < 15000"

  if (null != Class.forName(db_driver)) {
    println("!! class driver loaded")
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
    println(" class driver NOT loaded")
  }

  println("#################### Completed!! ############################")
  logger.debug("Process Completed Successfully !!!")
  spark.stop()
  logger.info(">> My Spark job stopped!")
}
