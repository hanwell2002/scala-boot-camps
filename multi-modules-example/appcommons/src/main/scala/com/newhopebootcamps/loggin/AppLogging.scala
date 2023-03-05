package com.newhopebootcamps.loggin

trait AppLogging {
  def info(msg: String) = {
    println(msg)
  }

  def debug(msg: String) = {
    println(msg)
  }

  def warn(msg: String) = {
    println(msg)
  }

}
