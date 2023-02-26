package com.newhopebootcamps.dao

import com.newhopebootcamps.config.{ApplicationConfiguration, Logging}
import java.sql.{Connection, DriverManager, SQLException}

class Dao extends Logging {
  private val db_url = ApplicationConfiguration.url
  private val db_username = ApplicationConfiguration.username
  private val db_password = ApplicationConfiguration.password

  private val connection: Connection = getConnection

  def getConnection: Connection = {
    logger.debug(s"connection: url=$db_url, username=$db_username, pwd = $db_password")

    try {
      val conn: Connection = DriverManager.getConnection(db_url, db_username, db_password)
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
