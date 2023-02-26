package application

import scalikejdbc._
import models.City
import org.slf4j.LoggerFactory
import com.typesafe.config.{Config, ConfigFactory}

/**
 * Generate City model from table city
 * Command:
 *    sbt "scalikejdbcGen city"
 *
 * Or specify class name: City
 *    sbt "scalikejdbcGen city City"
 *
 */
object CityMayor extends App {
  private val logger = LoggerFactory.getLogger(this.getClass)
  logger.info("@@@ ### >>> CityMayor started to work...")

  val applicationConf: Config = ConfigFactory.load("application.conf")
  val db_schema = applicationConf.getString("db_config.url")
  val db_user = applicationConf.getString("db_config.user")
  val db_password = applicationConf.getString("db_config.password")
  //println(db_password)
  ConnectionPool.singleton(db_schema, db_user, db_password)
  // ConnectionPool.singleton("jdbc:postgresql://localhost:5432/postgres", "postgres", "MY_PASSWORD")

  // find all cities Map to City
  private val cities = City.findAll()
  // cities.foreach(println)

  private val COUNTRY_CODE = "CAN"
  // filter out city of Canada which countrycode is CAN
  private val canadaCities = cities.filter(_.countrycode == COUNTRY_CODE)
  private var indexS = 0
  for (city <- canadaCities) {
    indexS += 1
    println(s" Canada City #$indexS.-> ${city.name}, ${city.countrycode}, ${city.district}, ${city.population}")
    logger.info(s" Canada City #$indexS -> ${city.name}, ${city.countrycode}, ${city.district}, ${city.population}")
    Thread.sleep(100)
    logger.debug("------------Beautiful Divide Line -------------")
    Thread.sleep(100)

  }

  logger.warn("Nothing to warn :)")
  logger.trace("Log Level is set to debug~, so this line won't appear in log.")

  import purecsv.safe._
  logger.debug(canadaCities.toCSV())
  canadaCities.writeCSVToFileName(
    "Canada_Cities.csv"
    , header = Some(Seq("ID", "City", "Code", "Province", "Population"))
  )

  logger.info("==========================Completed Normally!==================================")
}