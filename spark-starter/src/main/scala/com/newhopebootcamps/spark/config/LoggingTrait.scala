package com.newhopebootcamps.spark.config

import org.apache.logging.log4j.scala._

trait LoggingTrait extends Logging {
  override protected val logger: Logger = Logger(getClass)
  protected val log = logger
}