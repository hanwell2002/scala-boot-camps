package com.newhopebootcamps.loggin

import org.slf4j.LoggerFactory;
trait LoggingUtil {
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
