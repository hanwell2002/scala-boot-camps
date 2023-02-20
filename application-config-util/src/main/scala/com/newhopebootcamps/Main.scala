package com.newhopebootcamps

import com.newhopebootcamps.dao.{CityDao, CountryDao, TraderDao}
import com.newhopebootcamps.entity.Trader
import com.newhopebootcamps.helper.CsvDemoHelper
import com.newhopebootcamps.service.EmailService
import com.newhopebootcamps.util.{CommonUtils, CsvFileUtil}
import org.slf4j.LoggerFactory

import java.sql.SQLException
import java.util

object Main {
  val logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    logger.info("Application Started................")
    generateReport
    // findByContinent
    demoUserCrud
    // CsvDemoHelper.write("/opt/bootcamps/reports/demo.csv")
    // register("hanwell@newhopebootcamps.com")

    logger.info("Application Completed.................")
    println("Report generated!")

   for( a <- 1 to 2){
      log
      Thread.sleep(3000) // wait for 1000 millisecond
    }
  }

  def log = {
    logger.trace("A TRACE Message")
    logger.debug("A DEBUG Message")
    logger.info("An INFO Message")
    logger.warn("A WARN Message")
    logger.error("An ERROR Message")
  }
  def demoUserCrud(): Unit = {
    val dao = new TraderDao

    try {
      // dao.createTable() //only run once, other wise will throw out exception.
      // dao.insertTraders(List(new Trader(2, "Joe", "Joe.Biden@gmail.com", "USA", "password123"), new Trader(3, "Bush", "Bush@gmail.com", "US", "password123")))
      dao.updateRecord
      //dao.insertRecord

      dao.getAll()

    } catch {
      case e: SQLException => logger.error(e.getMessage)
      case _: Throwable => logger.error("Fatal: Unknown exception!")
    }

  }

  private def generateReport(): Unit = {
    // Find COUNTRY by Continent
    val sql = "SELECT * from country WHERE continent='North America'"
    val dao = new CountryDao
    val resultSet = dao.findByQuery(sql)

    val csvName = composeFileName
    logger.info(csvName)
    CsvFileUtil.writeAll(resultSet, csvName)

    // Find CITY by Country Code
    val countrycode = "USA"
    val query = s"SELECT * from city where countrycode='${countrycode}'"
    val cityDao = new CityDao
    cityDao.findByQuery(query)
  }

  @throws[SQLException]
  def findByContinent = {
    // Find COUNTRY by Continent
    val continentName = "North America"
    val dao = new CountryDao
    dao.quickFind(continentName)
  }

  def composeFileName: String = {
    val dateString = CommonUtils.currentDateString("yyyy-MM-dd")
    "/opt/bootcamps/reports/city_" + dateString + ".csv"
  }

  def register(email: String): Unit = {
    val service = new EmailService
    service.register(email)
  }

}