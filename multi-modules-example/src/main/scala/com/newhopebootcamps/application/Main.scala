package com.newhopebootcamps.application

import com.newhopebootcamps.loggin.LoggingUtil
//import org.apache.logging.log4j.scala.Logging

object Main extends App with LoggingUtil {
  logger.info(s"There are ${args.length} commandline arguments!")
  println(s"There are ${args.length} commandline arguments!")

  if (args.length >= 1) {
    logger.info(s"There are ${args.length} commandline arguments!")
    for (i <- 0 to args.length - 1) {
      var commandParam = args(i)
      logger.info(" + $commandParam")
    }

      if (args(0).equalsIgnoreCase("Producer")) {
        com.newhopebootcamps.stream.KafkaScalaProducer.main(Array("Producer"))
      } else if (args(0).equalsIgnoreCase("Consumer")) {
        com.newhopebootcamps.stream.KafkaScalaConsumer.main(Array("Consuer"))
      }

    args(0) match {
      case "Producer" => com.newhopebootcamps.stream.KafkaScalaProducer.main(Array("Producer"))
      case "Consumer" => com.newhopebootcamps.stream.KafkaScalaConsumer.main(Array("Consuer"))
      case _ => logger.info("Usage:  \n Producer or Consumer")
    }

    if (args.length >= 2) {
      args(1) match {
        case "Producer" => com.newhopebootcamps.stream.KafkaScalaProducer.main(Array("Producer"))
        case "Consumer" => com.newhopebootcamps.stream.KafkaScalaConsumer.main(Array("Consuer"))
        case _ => logger.info("Usage:  \n Producer or Consumer")
      }
    }
  }
  else {
    com.newhopebootcamps.stream.KafkaScalaProducer.main(Array("Producer"))
  }

  logger.info("Usage:  \n Producer \n Consumer")

  logger.info("************* END ****************")
}
