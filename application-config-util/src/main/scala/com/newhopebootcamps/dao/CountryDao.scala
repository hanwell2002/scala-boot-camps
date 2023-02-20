package com.newhopebootcamps.dao

import com.newhopebootcamps.util.DbUtils

import java.sql.{ResultSet, SQLException}

class CountryDao extends Dao {
  def findByQuery(query: String): ResultSet = {
    var resultSet: ResultSet = null
    try {
      // create the statement, and run the query
      val statement = getConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
      resultSet = statement.executeQuery(query)

      DbUtils.dumpResultSet(resultSet)

      closeConnection

    } catch {
      case e: SQLException => logger.error("SQLException: %s", e.getMessage)
      case _: Throwable => logger.error("Exception: Unknown exception.")

        return null
    }

    resultSet
  }

  def quickFind(parameter: String) = {
    log.info("Info: {} use thread {} to dispatch", "Calling read()", Thread.currentThread.getName)
    // val query = "SELECT * from country WHERE continent = ?"
    val query = "SELECT * from country WHERE continent = ? AND population < ?  ORDER BY ? DESC"
    /*  sqlinjection
        abc' or '1' = '1

        select customer_id
        , acc_number
        , branch_id
        , balance
        from Accounts where customerId = 'abc
        'or
        '1' = '1'
      */

    // val query= "SELECT * from country WHERE continent = '" + parameter + "'";
    // '1' or true,  abc' or '1' = '1
    //    val query3= String.format("city name = %20s, country code = %s", "abc", "bush")
    //
    //execution plan

    try {
      // create the statement, and run the query
      val connection = getConnection;

      val statement = connection.prepareStatement(query)
      statement.setString(1, parameter)
      statement.setInt(2, 18000)
      statement.setString(3, "name")

      val resultSet = statement.executeQuery()
      while (resultSet.next) {
        val code = resultSet.getString("code")
        val name = resultSet.getString("name")
        val population = resultSet.getInt("population")
        logger.info("city name = %30s, country code = %s,  population= %d".format(name, code, population))
      }

      closeConnection
    } catch {
      case e: SQLException => logger.error("SQLException=: %s", e.getMessage)
      case _: Throwable => logger.error("Exception: Unknown exception.")
    }
  }

  class Country(var name: String)

  //write a country class
  def add(obj: Country) = {
    // todo add a new country to Table

    val query = "INSERT INTO public.country (code, \"name\", continent, region, surfacearea, indepyear, population, lifeexpectancy, gnp, gnpold, localname, governmentform, headofstate, capital, code2)  VALUES(?, ?, ?, ?, 0, 0, 0, 0, 0, 0, ?,?,?, 0, ?)"


  }

  def update(obj: Country) = {

    // todo add a new country to Table, change the population to 18000
    // Turks and Caicos Islands, country code = TCA,  population= 17000

    val query = "UPDATE public.country SET population=? WHERE name = ? "
    val statement = getConnection.prepareStatement(query)

    // obj.population

    /*
        statement.setInt(1, obj.population)
        statement.setString(2, "real country name")
    */


  }


}