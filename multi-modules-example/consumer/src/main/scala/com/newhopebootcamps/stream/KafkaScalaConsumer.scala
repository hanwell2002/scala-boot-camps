package com.newhopebootcamps.stream

import com.newhopebootcamps.loggin.AppLogging

import java.util.{Collections, Properties}
import scala.collection.JavaConverters._
import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}

import java.time.Duration

object KafkaScalaConsumer extends App with AppLogging with KafkaSettings {
  info("### Kafka Consumer started ...... ")

  info("the_server_port= " + the_server_port)
  info("the topic= " + the_topic)
  val props = new Properties()
  //  props.put("bootstrap.servers", "192.168.214.130:9092")
  props.put("bootstrap.servers", the_server_port)
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("group.id", "consumer-group-1")
  props.put("enable.auto.commit", "true")
  props.put("auto.commit.interval.ms", "1000")
  props.put("auto.offset.reset", "earliest")
  props.put("session.timeout.ms", "30000")
  val topic = the_topic
  /*
  // subscrible single topic
  val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
  consumer.subscribe(Collections.singletonList(topic))
  while (true) {
    val records: ConsumerRecords[String, String] = consumer.poll(Duration.ofMillis(100))
    for (record <- records.asScala) {
      info(record)
    }
  }
  */
  val consumer = new KafkaConsumer[String, String](props)
  //subscrible multiple topics
  val topics = Seq(the_topic, json_topic)
  consumer.subscribe(topics.asJava)

  while (true) {
    val records = consumer.poll(Duration.ofMillis(100)).asScala
    for (record <- records) {
      info(s"Message: (key: ${record.key()} with value: ${record.value()} @ partition ${record.partition()} @ offset ${record.offset()}")
      // info(record)
    }
  }

  info("Kafka consumer ended ......")
}