package com.newhopebootcamps.spark

import com.newhopebootcamps.spark.config.ApplicationConfiguration
import java.util.Calendar
import org.apache.spark.sql.SparkSession
import java.text.SimpleDateFormat

import org.apache.logging.log4j.scala.Logging
import org.apache.logging.log4j.Level
object PostgresDataFrameDemo extends App with Logging {
 //  def logger = LoggerFactory.getLogger("PostgresDataFrameDemo")
  logger.info("@@@@@@@@@@@@@@@@PostgresDataFrameDemo Started......")
  // Tell spark which driver to use
  private val db_driver = ApplicationConfiguration.driver //"org.postgresql.Driver"
  private val db_url = ApplicationConfiguration.url //"jdbc:postgresql://localhost:5432/postgres"
  private val db_username = ApplicationConfiguration.username //postgres
  private val db_password = ApplicationConfiguration.password // password for postgres

  val format = new SimpleDateFormat("yyyyMMddhhmmss")
  val time_stampe_for_file = format.format(Calendar.getInstance().getTime())
  val csvFileTo = "/opt/bootcamps/reports/output/" // Should be some file on your system
  // val csvcity = csvFileTo + "city_" + time_stampe_for_file + ".csv"
  // val csvcountry   = csvFileTo + "country_"   + time_stampe_for_file + ".csv"
  val jsonFile = "/opt/bootcamps/reports/outputjson/tb_json.json" // Should be some file on your system
  val conf_loc = "/opt/data/config/spark/fortune100.cfg"

  val spark = SparkSession.builder()
     .master("local[2]")
    .appName("PostgresDataFrameDemo")
    .config("spark.jars", "/opt/bootcamps/lib/postgresql-42.5.2.jar")
    .getOrCreate();

  if (Class.forName(db_driver) != null) {
    logger.info("!! class driver loaded")

    //    val queryCountry = s"select * from public.country where population < 20000"
    val queryCountry = s"select * from public.country WHERE 1=1"
    val df_country = spark.read
      .format("jdbc")
      .option("url", db_url)
      .option("user", db_username)
      .option("password", db_password)
      .option("dbtable", s"($queryCountry) as t")
      .load()
    df_country.createOrReplaceTempView("table_country")

    val csvEmp = csvFileTo + "country_" + time_stampe_for_file + ".csv"
    df_country.write
      .option("header", true)
      .csv(csvEmp)

    df_country.show()
    logger.info("country export to csv!" + csvEmp)

    // val queryCity = s"select * from public.city where countrycode='CHN'"
    val queryCity = s"select * from public.city"
    val df_city = spark.read
      .format("jdbc")
      .option("url", db_url)
      .option("user", db_username)
      .option("password", db_password)
      .option("dbtable", s"($queryCity) as t")
      .load()

    df_city.createOrReplaceTempView("table_city")

    val csvDep = csvFileTo + "city_" + time_stampe_for_file + ".csv"
    df_city.write
      .option("header", true)
      .csv(csvDep)

    df_city.show()
    logger.info("city export to csv =>" + csvDep)

    val rpt_sql =
      """select e.code, e.name, d.name as city
         from table_country e, table_city d
         where e.code=d.countrycode
         and e.code='CHN'
      """

    val df_report = spark.sql(rpt_sql)

    val csv_report = csvFileTo + "report_" + time_stampe_for_file
    df_report.show()
    df_report.write.option("header", true)
      .csv(csv_report)

  } else {
    logger.info(" class driver NOT loaded")
  }

  logger.info("http://www.newhopebootcamps.com #################### Completed!! ############################")

  spark.stop()
}
