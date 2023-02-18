package com.newhopebootcamps.dao

import com.newhopebootcamps.config.{ApplicationConfiguration, Logging}

import java.sql.{Connection, DriverManager, SQLException}

class Dao extends Logging {
  val url = ApplicationConfiguration.url
  val username = ApplicationConfiguration.username
  val password = ApplicationConfiguration.password

  private val connection: Connection = getConnection

  def getConnection: Connection = {
    logger.debug(s"connection: url=$url, username=$username, pwd = $password")

    try {
      val conn: Connection = DriverManager.getConnection(url, username, password)
      conn
    } catch {
      case e: SQLException => logger.error(e.getMessage)
      null
    }
  }

  def closeConnection(): Unit = {
    if (connection != null) {
      connection.close()
    }
  }

}
