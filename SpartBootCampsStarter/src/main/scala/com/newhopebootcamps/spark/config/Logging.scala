package com.newhopebootcamps.spark.config

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
class Logging {
  val logger = LoggerFactory.getLogger(this.getClass)
  //alias
  val log = logger

  def demo(msg:String): String = {
    logger.error("Error message")
    logger.warn("Warn message")
    logger.trace("Trace message")
    logger.debug("Debug message")
    logger.info("Info message example.")
    msg
  }
}
