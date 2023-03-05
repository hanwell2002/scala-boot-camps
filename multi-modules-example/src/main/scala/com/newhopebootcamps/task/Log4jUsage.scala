package com.newhopebootcamps.task

import com.newhopebootcamps.tools.ConsumerLab
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.scala.Logging

trait Greeting {
  lazy val greeting: String = "www.newhopebootcamps.com "
}

// object Log4jUsage extends Greeting with App with Logging {
object Log4jUsage extends Greeting with App with Logging {

  someFuntion()

  someFuntionWithLogLevel(Level.INFO)
  someFuntionWithLogLevel(Level.DEBUG)
  someFuntionWithLogLevel(Level.TRACE) //Won't show due to lower level than DEBUG defined in log4j config file.
  someFuntionWithLogLevel(Level.WARN)
  someFuntionWithLogLevel(Level.ERROR)

  // callFunctionFormOtherModule

  def someFuntion(): Unit = {
    logger.info("log info example!")
  }

  def someFuntionWithLogLevel(level: Level): Unit = {
    logger(level, s"Log message with arbitrary level " + level.name())
  }

  def callFunctionFormOtherModule = {
    val text_file_name = "/opt/bootcamps/data/photo.txt"

    logger.info("Calling function implemented in commons module.")
    val lab = new ConsumerLab
    lab.printOutFile(text_file_name)
    logger.info("Called function in commons module.")
  }
}

