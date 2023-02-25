package com.newhopebootcamps.spark.config

import com.typesafe.config.ConfigFactory
import com.newhopebootcamps.spark.encryption.EncryptionUtil

import java.io.File
import scala.util.control.ControlThrowable

object ApplicationConfiguration {
  var driver: String = ""
  var url: String = ""
  var username: String = ""
  var password: String = ""
  var key: String = ""
  var initVector: String = ""

  private val loaded = initialize

  def initialize(): Boolean = {
    try {
      val defaults = ConfigFactory.load()
      val file = new File(defaults.getString("appinfo.config"))
      val conf = ConfigFactory.parseFile(file).withFallback(defaults)

      key = conf.getString("secure.key")
      initVector = conf.getString("secure.initVector")
      println(s"key=$key, initVector = $initVector")

      driver = conf.getString("database_postgres.driver")
      url = conf.getString("database_postgres.url")
      username = conf.getString("database_postgres.username")
      val pwd = conf.getString("database_postgres.password")
      password = EncryptionUtil.decrypt(pwd)

      println(s"Initialize: url=$url, username=$username, pwd = $password")

      val appName = conf.getString("appinfo.name")
      println(s"appName=$appName")


    // } catch  safely {
    } catch  {
       case ex: Throwable => println("ERROR: 777 > " + ex.getMessage)
    //   case ex: Throwable => println("ERROR: Failed to initialize application config!")
     // case _: Throwable => logger.error("Exception: Failed to initialize application config!!")
      false
    }
    true
  }

  def safely[T](handler: PartialFunction[Throwable, T]): PartialFunction[Throwable, T] = {
    case ex: ControlThrowable => throw ex
    case ex: Throwable if handler.isDefinedAt(ex) => handler(ex)
    // If they didn't handle it, rethrow. This line isn't necessary, just for clarity
    case ex: Throwable => throw ex
  }

}

