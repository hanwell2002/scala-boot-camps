package com.newhopebootcamps.stream

trait KafkaSettings {
  val the_host = "192.168.214.130"
  val the_port = "9092"
  val the_topic = "newhope.bootcamps"
  val json_topic = "json_topic"


  val the_server_port = s"$the_host:$the_port"
}
