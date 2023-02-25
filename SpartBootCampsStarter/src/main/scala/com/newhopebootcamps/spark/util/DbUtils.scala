package com.newhopebootcamps.spark.util

import java.sql.ResultSet

object DbUtils {
  def dumpResultSet(rs: ResultSet):Unit = {
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

    println(dataList)
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

}
