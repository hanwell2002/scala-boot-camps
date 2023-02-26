package com.newhopebootcamps.helper

import com.github.tototoshi.csv._
import com.newhopebootcamps.config.Logging
import com.newhopebootcamps.util.CommonUtils.isEmpty

import java.io._
import java.sql.ResultSet

object CsvDemoHelper extends Logging{
  def read(filePath: String): Unit = {
    logger.debug("Reading CSV")
    val reader = CSVReader.open(new File(filePath))
    //1.
    // reader.foreach(logger.debug)
    //2.
    // val all =  reader.all()
    // all.foreach(fields => logger.debug(fields))
    //3.
    val it = reader.iterator
    while (it.hasNext) {
      val row = it.next
      row.foreach {
        col => print(col + " ")
      }
      println("")
    }

    reader.close()
    logger.debug("Completed reading CSV")
  }

  def write(filePath: String): Unit = {
    logger.debug("Writing CSV ...... ")

    try {
      val writer = CSVWriter.open(new FileWriter(filePath))
      writer.writeAll(List(List("a", "b", "c"), List("d", "e", "f"), List("1", "2", "3")))
    } catch {
      case e: IOException => logger.error("Failed to write file!: " + e.getMessage)
      case _: Throwable => logger.error("Exception: Unknown!")
    } finally {
      logger.info(s"Write csv: $filePath")
    }
  }

  def rsWrite(rs: ResultSet, headerString: String, filePath: String): Unit = {
    val csvWriter = CSVWriter.open(new FileWriter(filePath))
    var rl: List[List[String]] = List.empty

    if (!isEmpty(headerString)) {
      val header = headerString.split(",").toList
      rl :+= header
      logger.debug("CSV Header: ")
      header.foreach(logger.debug)
    }
    else
      logger.debug("CSV no header")

    while (rs.next) {
      var rec: List[String] = List.empty[String]
      val countryName = rs.getString("code")
      val location = rs.getString("region")

      rec :+= countryName
      rec :+= location

      rl :+= rec
    }

    csvWriter.writeAll(rl)
  }

  @scala.annotation.tailrec
  def getResult(resultSet: ResultSet, list: List[String] = Nil): List[String] = {
    if (resultSet.next()) {
      val value = resultSet.getString(1)
      getResult(resultSet, value :: list)
    }
    else {
      list
    }
  }

}
