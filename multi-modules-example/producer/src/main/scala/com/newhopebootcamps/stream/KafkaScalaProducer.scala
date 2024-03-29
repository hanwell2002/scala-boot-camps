package com.newhopebootcamps.stream

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import com.newhopebootcamps.model.Order
import com.google.gson.Gson
import com.newhopebootcamps.logging.LoggingUtil
import utility.config._

object KafkaScalaProducer extends App with LoggingUtil {
  logger.info(args(0))

  logger.info("### Kafka Producer Started ......")
  val props = new Properties()
  //  val url = "192.168.214.130:9092"
  val url = AppConfig.kafka_url
  val clientId = AppConfig.kafka_client_id

  logger.info(s"Kafka Server: url: $url,  clientId: $clientId")

  val host = AppConfig.kafka_host
  val port = AppConfig.kafka_port

  // props.put("bootstrap.servers", url) // "192.168.214.130:9092"
  props.put("bootstrap.servers", host + ":" + port) // "192.168.214.130:9092"
  props.put("client.id", clientId)
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("acks", "all")
  props.put("retries", "0")
  props.put("batch.size", AppConfig.kafka_batch_size)
  props.put("linger.ms", "1")
  props.put("buffer.memory", "33554432")

  val producer: KafkaProducer[Nothing, String] = new KafkaProducer[Nothing, String](props)
  val topic = AppConfig.kafka_topic_trade

  logger.info(s"Sending Records to Kafka Topic: [$topic]")
  val gson = new Gson

  val order = Order("Order #" + 1, 12.0 * 2, "Bill Swan", "20220202")

  val orderJson = gson.toJson(order);

  println("@@@@@@@@ json string: " + orderJson)

  for (i <- 1 to 99) {

    Thread.sleep(2000)

    val order = Order("Order #" + i, 12.0 * i, "Bill Swan", "20220202")
    // ---------example-1: Send message to topic boot.camps.trade-----------
    val record: ProducerRecord[Nothing, String] = new ProducerRecord(topic, order.toString())
    logger.info(s"Sending in String ####> $record")
    producer.send(record)
    // ---------example-2. send message with Json format to topic boot.camps.json----------
    val json_record: ProducerRecord[Nothing, String] = new ProducerRecord(AppConfig.kafka_topic_json, gson.toJson(order))
    producer.send(json_record)
    logger.info(s"Sent in Json ==> $json_record")
  }


  /**
   * Close this producer. This method blocks until all previously sent requests complete.
   */
  producer.close()

  logger.info("Kafka Producer closed ......")
}
