package application

import scalikejdbc._
import models.City
import org.slf4j.LoggerFactory
//import org.slf4j.impl.StaticLoggerBinder
//import ch.qos.logback.core.util.StatusPrinter
//import ch.qos.logback.classic.LoggerContext

/**
 * Generate Cityp model from table city
 * Command:
 *    sbt "scalikejdbcGen city"
 *
 * Or specify class name: City
 *    sbt "scalikejdbcGen city City"
 *
 */

// scala -classpath target/scala-2.12/orm-reverse-engine-lab_2.12-1.0.1.jar application.CityMayor
object CityMayor extends App {
  ConnectionPool.singleton("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres", "Admin$777")

  val logger = LoggerFactory.getLogger(this.getClass)
  logger.info("@@@ ### >>> CityMayor started to work...")

  // find all cities Map to City
  val cities = City.findAll()
  // cities.foreach(println)

  val CITY_CODE = "CAN"
  // filter out city of Canada which countrycode is CAN
  val canadaCities = cities.filter(_.countrycode == CITY_CODE)
  var indexS = 0
  for (city <- canadaCities) {
    indexS += 1
    println(s" Canda City #$indexS.-> ${city.name}, ${city.countrycode}, ${city.district}, ${city.population}")
    logger.info(s" Canda City #$indexS -> ${city.name}, ${city.countrycode}, ${city.district}, ${city.population}")
  }

  println("==========================Completed Normally!==================================")

}