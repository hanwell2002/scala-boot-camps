package com.newhopebootcamps.config

import ch.qos.logback.classic.{Level, Logger}
import org.slf4j.LoggerFactory

// import com.typesafe.scalalogging.LazyLogging
// import com.typesafe.scalalogging.slf4j.LazyLogging
class Logging {
  //class Logging extends LazyLogging{
  val logger = LoggerFactory.getLogger(this.getClass)
  // val logger = com.typesafe.scalalogging.Logger(getClass)
  val log = logger

  def demo(msg:String): String = {
    logger.trace("A TRACE Message")
    logger.debug("A DEBUG Message")
    logger.info("An INFO Message")
    logger.warn("A WARN Message")
    logger.error("An ERROR Message")
    msg
  }
}
