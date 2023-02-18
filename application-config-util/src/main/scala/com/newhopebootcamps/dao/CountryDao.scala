package com.newhopebootcamps.dao

import java.sql.{ResultSet, SQLException}

class CountryDao extends Dao {
  def read(query: String): ResultSet = {
    var resultSet: ResultSet = null
    try {
      // create the statement, and run the query
      val statement = getConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
      resultSet = statement.executeQuery(query)
      /*
            while (resultSet.next) {
              val countryName = resultSet.getString("name")
              val location = resultSet.getString("region")
              // logger.debug("country-name = %30s, region = %s".format(countryName, location))
            }

            resultSet.last()
            println("rows=" + resultSet.getRow)
            resultSet.beforeFirst()*/

      closeConnection

    } catch {
      case e: SQLException => logger.error("SQLException: %s", e.getMessage)
      case _: Throwable => logger.error("Exception: Unknown exception.")

        null
    }

    resultSet
  }
}
