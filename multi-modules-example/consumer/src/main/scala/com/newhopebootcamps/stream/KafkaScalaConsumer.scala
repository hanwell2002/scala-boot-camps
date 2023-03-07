package com.newhopebootcamps.stream

import com.newhopebootcamps.loggin.{AppLogging, LoggingUtil}

import java.util.{Collections, Properties}
import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import utility.config.AppConf

import java.time.Duration

object KafkaScalaConsumer extends App with LoggingUtil with KafkaSettings {
  logger.info("### Kafka Consumer started ...... ")
  val url = AppConf.kafka_url
  val clientId = AppConf.kafka_client_id
  val batchSize = AppConf.kafka_batch_size
  val topicTrade = AppConf.kafka_topic_trade
  val topicJson = AppConf.kafka_topic_json
  logger.info(s">> url: $url,  clientId: $clientId, batch-size: $batchSize")

  val props = new Properties()
  //  props.put("bootstrap.servers", "192.168.214.130:9092")
  props.put("bootstrap.servers", url)
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("group.id", "consumer-group-1")
  props.put("enable.auto.commit", "true")
  props.put("auto.commit.interval.ms", "1000")
  props.put("auto.offset.reset", "earliest")
  props.put("session.timeout.ms", "30000")

  /*
  // subscrible single topic
  val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
  val topic =  AppConf.kafka_topic_trade
  consumer.subscribe(Collections.singletonList(topic))
  while (true) {
    val records: ConsumerRecords[String, String] = consumer.poll(Duration.ofMillis(100))
    for (record <- records.asScala) {
      logger.info(record)
    }
  }
  */
  val consumer = new KafkaConsumer[String, String](props)
  //subscrible multiple topics
  val topics = Seq(topicTrade, topicJson)
  consumer.subscribe(topics.asJava)

  while (true) {
    val records = consumer.poll(Duration.ofMillis(100)).asScala
    for (record <- records) {
      logger.info(s"Message: (key: ${record.key()} with value: ${record.value()} @ partition ${record.partition()} @ offset ${record.offset()}")
      // logger.info(record)
    }
  }

  logger.info("Kafka consumer ended ......")
}