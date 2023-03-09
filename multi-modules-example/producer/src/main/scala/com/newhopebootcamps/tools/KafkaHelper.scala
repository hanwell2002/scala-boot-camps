package com.newhopebootcamps.tools

import com.newhopebootcamps.logging.LoggingUtil
import utility.config.LoadConfiguration

object KafkaHelper extends App with LoggingUtil {
  //vm:options:  -DAPPLICATION_EXTRA_CONFIG=/opt/bootcamps/config/application.cfg
  val env_var = "APPLICATION_EXTRA_CONFIG"

  loadConfig(env_var)
  printEnvAndSysPrp

  logger.info("----------------- completed ------------------")
  def loadConfig(env_variable: String): Unit = {
    val appConfig = new LoadConfiguration()
    val value = appConfig.envOrElseConfig(env_variable)
    println(s"APPLICATION_EXTRA_CONFIG $value")
  }

  def printEnvAndSysPrp = {
    import scala.collection.JavaConverters._
    import scala.language.implicitConversions

    logger.warn("\n################## environment vars #####################")
    val environmentVars = System.getenv().asScala
    for ((k, v) <- environmentVars)
      logger.debug(s"key: $k, value: $v")

    logger.warn("\n##################  system properties #####################")
    val properties = System.getProperties().asScala
    for ((k, v) <- properties)
      logger.debug(s"key: $k, value: $v")
  }


}
