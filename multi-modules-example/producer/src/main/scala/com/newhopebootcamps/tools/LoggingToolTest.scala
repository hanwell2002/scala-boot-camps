package com.newhopebootcamps.tools

import utility.logging._

object LoggingToolTest extends  App with LoggingTool{

  //with trait
  info("Holla!")


  //singleton object
  LogUtilis.info("Hello!")


  //instantiate a object log
  var log  = new LogTools

  log.debug("Scala Class example. Debug!")

}
