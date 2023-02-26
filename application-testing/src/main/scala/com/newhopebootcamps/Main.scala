package com.newhopebootcamps

import com.newhopebootcamps.dao.{CityDao, CountryDao, TraderDao}
import com.newhopebootcamps.encryption.CubeCalculator
import com.newhopebootcamps.entity.Trader
import com.newhopebootcamps.helper.CsvDemoHelper
import com.newhopebootcamps.service.EmailService
import com.newhopebootcamps.util.{CommonUtils, CsvFileUtil}
import org.slf4j.LoggerFactory

import java.sql.SQLException
import java.util

object Main {
  private val logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    logger.info("Application Started................")


   val cubRet =  CubeCalculator.cube(3)
    if(cubRet == 27) {
      println("pass")
    }else {
      println("Failed")
    }

    val cubRet2 = CubeCalculator.cube(2)
    if (cubRet == 8) {
      println("pass")
    } else {
      println("Failed")
    }


    generateReport
    println(">> Report generated!")

    // findByContinent
    demoUserCrud

    // CsvDemoHelper.write("/opt/bootcamps/reports/demo.csv")
    // register("hanwell@newhopebootcamps.com")

    // keep app up to check report inside docker container
    val period = 5; //200000
    for (t <- 1 to period) {
      log
      Thread.sleep(2000) // wait for 2000 millisecond
    }

    logger.info("#..........................................................#")
    logger.info("|................... Application Completed.................|")
    logger.info("#..........................................................#")
  }

  def log = {
/*
    logger.error("Error message")
    logger.warn("Warn message")
    logger.trace("Trace message")
    logger.debug("Debug message")
*/
    logger.info("Application is running ...")
  }

  def demoUserCrud(): Unit = {
    val dao = new TraderDao

    try {
      dao.createTable() //only run once, other wise will throw out exception.
      dao.addOne
      dao.batchAdd(List(new Trader(2, "Joe", "Joe.Biden@gmail.com", "USA", "password123"), new Trader(3, "Bush", "Bush@gmail.com", "US", "password123")))
      dao.update
      dao.findAll()

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