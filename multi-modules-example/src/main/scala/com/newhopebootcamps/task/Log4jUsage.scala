package com.newhopebootcamps.task

import com.newhopebootcamps.logging.LoggingUtil
import com.newhopebootcamps.tools.ConsumerLab

import java.util.logging.Level
//import org.apache.logging.log4j.Level
//import org.apache.logging.log4j.scala.Logging

trait Greeting {
  lazy val greeting: String = "www.newhopebootcamps.com "
}

// object Log4jUsage extends Greeting with App with Logging {
object Log4jUsage extends Greeting with App with LoggingUtil {

  someFuntion

  callFunctionFormOtherModule
  def someFuntion(): Unit = {
    logger.info("log info example!")
  }

  def callFunctionFormOtherModule = {
    val text_file_name = "/opt/bootcamps/data/photo.txt"

    logger.info("Calling function implemented in commons module.")
    val lab = new ConsumerLab
    lab.printOutFile(text_file_name)
    logger.info("Called function in commons module.")
  }
}

