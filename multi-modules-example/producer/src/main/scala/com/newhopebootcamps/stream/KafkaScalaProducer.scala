package com.newhopebootcamps.stream

import java.util.Properties
import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import com.newhopebootcamps.model.Order
import com.google.gson.Gson
import com.newhopebootcamps.loggin.LoggingUtil

object KafkaScalaProducer extends App with LoggingUtil with KafkaSettings {

  logger.info("### Kafka Producer Started ......")
  val props = new Properties()

  logger.info("the_server:port= " + the_server_port) //"192.168.214.130:9092"

  props.put("bootstrap.servers", the_server_port)
  props.put("client.id", "ScalaProducerExample")
  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  props.put("acks", "all")
  props.put("retries", "0")
  props.put("batch.size", "16384")
  props.put("linger.ms", "1")
  props.put("buffer.memory", "33554432")

  val producer: KafkaProducer[Nothing, String] = new KafkaProducer[Nothing, String](props)
  val topic = the_topic

  logger.info(s"Sending Records to Kafka Topic: [$topic]")
  val gson = new Gson
  for (i <- 1 to 100) {
    Thread.sleep(1000)

    val order = Order("Order #" + i, 12.0 * i, "Bill Swan", "20220202")

    // --------- 1 -----------
    val record: ProducerRecord[Nothing, String] = new ProducerRecord(topic, order.toString())
    logger.info(s"Sending in String ####> $record")
    producer.send(record)

    // --------- 2. send in Json format----------
    val json_record: ProducerRecord[Nothing, String] = new ProducerRecord("json_topic", gson.toJson(order))
    producer.send(json_record)
    logger.info(s"Sent in Json ==> $json_record")
  }

  /**
   * Close this producer. This method blocks until all previously sent requests complete.
   */
  producer.close()

  logger.info("Kafka Producer closed ......")
}
