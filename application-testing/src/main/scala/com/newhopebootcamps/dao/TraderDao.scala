package com.newhopebootcamps.dao

import com.newhopebootcamps.entity.Trader

import java.sql.{DriverManager, SQLException}

/**
 * In this code, we have seen how to write a Scala program to connect to the PostgreSQL database and perform basic database operations
 * (CRUD - Create, Retrieve, Update and Delete) using JDBC (Java Database Connectivity) API
 */
class TraderDao extends Dao {

  val createTableSQL =
    """
      |CREATE TABLE traders (
      |  ID INT PRIMARY KEY
      |, NAME TEXT
      |, EMAIL VARCHAR(50)
      |, COUNTRY VARCHAR(50)
      |, PASSWORD VARCHAR(50)
      |)
      """.stripMargin


  private val INSERT_USERS_SQL = "INSERT INTO traders" + "  (id, name, email, country, password) VALUES " + " (?, ?, ?, ?, ?);"
  private val QUERY = "select id,name,email,country,password from Traders where id =?"
  private val SELECT_ALL_QUERY = "select * from traders"
  private val DELETE_USERS_SQL = "delete from traders where id = ?;"

  @throws[SQLException]
  def createTable(): Unit = {
    logger.info(createTableSQL)
    // Step 1: Establishing a Connection
    val connection = getConnection
    // Step 2:Create a statement using connection object
    val statement = connection.createStatement
    try
      // Step 3: Execute the query or update query
      statement.execute(createTableSQL)
    catch {
      case e: SQLException => writeSqlExceptionToLog(e)
    } finally {
      if (connection != null) connection.close()
      if (statement != null) statement.close()
    }

  }

  @throws[SQLException]
  def addOne: Unit = {
    logger.info(INSERT_USERS_SQL)
    // Step 1: Establishing a Connection
    val connection = getConnection
    // Step 2:Create a statement using connection object
    val preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)
    try {
      preparedStatement.setInt(1, 1)
      preparedStatement.setString(2, "ChatGPTony")
      preparedStatement.setString(3, "OpenAI@gmail.com")
      preparedStatement.setString(4, "USA")
      preparedStatement.setString(5, "USDD")
      println(preparedStatement)
      // Step 3: Execute the query or update query
      preparedStatement.executeUpdate
    } catch {
      case e: SQLException => writeSqlExceptionToLog(e)
    } finally {
      if (connection != null) connection.close()
      if (preparedStatement != null) preparedStatement.close()
    }

  }

  /**
   * insert multiple traders  List < User > list
   */
  @throws[SQLException]
  def batchAdd(list: List[Trader]): Unit = {
    val conn = getConnection
    val statement = conn.prepareStatement(INSERT_USERS_SQL)
    try {
      var count = 0
      for (user <- list) {
        statement.setInt(1, user.id)
        statement.setString(2, user.name)
        statement.setString(3, user.email)
        statement.setString(4, user.country)
        statement.setString(5, user.password)

        statement.addBatch

        count += 1
        // execute every 100 rows or less
        if (count % 100 == 0 || count == list.size) statement.executeBatch

      }
    } catch {
      case ex: SQLException => writeSqlExceptionToLog(ex)
        logger.info(ex.getMessage)
    } finally {
      if (conn != null) conn.close()
      if (statement != null) statement.close()
    }
  }

  @throws[SQLException]
  def findById(id: Int): Unit = {
    // using try-with-resources to avoid closing resources (boiler plate
    // code)
    // Step 1: Establishing a Connection
    val connection = getConnection
    // Step 2:Create a statement using connection object
    val preparedStatement = connection.prepareStatement(QUERY)
    try {
      preparedStatement.setInt(1, id)
      println(preparedStatement)
      // Step 3: Execute the query or update query
      val rs = preparedStatement.executeQuery
      // Step 4: Process the ResultSet object.
      while (rs.next) {
        val id = rs.getInt("id")
        val name = rs.getString("name")
        val email = rs.getString("email")
        val country = rs.getString("country")
        val password = rs.getString("password")
        logger.info(id + "," + name + "," + email + "," + country + "," + password)
      }
    } catch {
      case e: SQLException => writeSqlExceptionToLog(e)
    } finally {
      if (connection != null) connection.close()
      if (preparedStatement != null) preparedStatement.close()
    }
  }

    @throws[SQLException]
  def findAll(): Unit = {
    // using try-with-resources to avoid closing resources (boiler plate
    // code)
    // Step 1: Establishing a Connection
    val connection = getConnection
    // Step 2:Create a statement using connection object
    val preparedStatement = connection.prepareStatement(SELECT_ALL_QUERY)
    try {
      println(preparedStatement)
      // Step 3: Execute the query or update query
      val rs = preparedStatement.executeQuery
      // Step 4: Process the ResultSet object.
      while (rs.next) {
        val id = rs.getInt("id")
        val name = rs.getString("name")
        val email = rs.getString("email")
        val country = rs.getString("country")
        val password = rs.getString("password")
        logger.info(id + "," + name + "," + email + "," + country + "," + password)
      }
    } catch {
      case e: SQLException => writeSqlExceptionToLog(e)
    } finally {
      if (connection != null) connection.close()
      if (preparedStatement != null) preparedStatement.close()
    }

  }

  private val UPDATE_USERS_SQL = "update traders set name = ? where id = ?;"
  @throws[SQLException]
  def update(): Unit = {
    logger.info(UPDATE_USERS_SQL)
    // Step 1: Establishing a Connection
    val connection = getConnection
    // Step 2:Create a statement using connection object
    val preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)
    try {
      preparedStatement.setString(1, "Joe")
      preparedStatement.setInt(2, 2)
      // Step 3: Execute the query or update query
      preparedStatement.executeUpdate
    } catch {
      case e: SQLException => writeSqlExceptionToLog(e)
    } finally {
      if (connection != null) connection.close()
      if (preparedStatement != null) preparedStatement.close()
    }
    // Step 4: try-with-resource statement will auto close the connection.
  }

  @throws[SQLException]
  def delete(): Unit = {
    logger.info(DELETE_USERS_SQL)
    // Step 1: Establishing a Connection
    val connection = getConnection
    // Step 2:Create a statement using connection object
    val preparedStatement = connection.prepareStatement(DELETE_USERS_SQL)
    try {
      preparedStatement.setInt(1, 1)
      // Step 3: Execute the query or update query
      val result = preparedStatement.executeUpdate
      logger.info("Number of records affected :: " + result)
    } catch {
      case e: SQLException => writeSqlExceptionToLog(e)
    } finally {
      if (connection != null) connection.close()
      if (preparedStatement != null) preparedStatement.close()
    }
    // Step 4: try-with-resource statement will auto close the connection.
  }

  def writeSqlExceptionToLog(ex: SQLException): Unit = {

    logger.error(ex.getMessage)

  }

}
