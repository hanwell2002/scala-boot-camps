package com.newhopebootcamps.util

import java.io._
import java.sql.ResultSet
import com.github.tototoshi.csv._
import com.newhopebootcamps.config.Logging

object CsvFileUtil extends Logging {
  def writeAll(rs: ResultSet, filePath: String, isWriteHeader: Boolean = true): Unit = {

    try {
      val csvWriter = CSVWriter.open(new FileWriter(filePath))
      var rl: List[List[String]] = List.empty
      val columnNames = getColumnNames(rs)

      if (isWriteHeader) {
        rl :+= columnNames
      }

      rl ++= extractRecords(rs)
      csvWriter.writeAll(rl)
    } catch {
      case e: IOException => logger.error("Failed to write file!: " + e.getMessage)
      case _: Throwable => logger.error("Exception: Unknown!")
    } finally {
      logger.debug(s"Generated CSV Report: $filePath")
    }
  }

  def extractRecords(rs: ResultSet): List[List[String]] = {
    var dataList: List[List[String]] = List.empty
    val columnNames = getColumnNames(rs)
    while (rs.next) {
      var columnList: List[String] = List.empty[String]
      for (name <- columnNames) {
        val value = rs.getString(name)
        columnList :+= value
      }

      dataList :+= columnList
    }

    dataList
  }

  def getColumnNames(rs: ResultSet): List[String] = {
    var columnList: List[String] = List.empty[String]
    val metaData = rs.getMetaData
    val columnCount = metaData.getColumnCount

    val cols = (1 to columnCount).map { k =>
      val columnName = metaData.getColumnLabel(k)
      columnList :+= columnName
    }

    columnList
  }

  def tmp(): Unit = {
    def inlineMeAgain[T](f: => T): T = {
      f
    }

    def inlineme(f: => Int): Int = {
      try {
        inlineMeAgain {
          return f
        }
      } catch {
        case ex: Throwable => 5
      }
    }

    def doStuff {
      val res = inlineme {
        10
      }
      println("we got: " + res + ". should be 10")
    }
    doStuff


  }

}
