package utility.config

import utility.config.ApplicationConfiguration.kafka_url

import com.typesafe.config.ConfigFactory
import java.io.File

object AppConf {
  val defaults = ConfigFactory.load()

  val fromSource = ConfigFactory.parseResources("kafka.conf")

  println("AppConf: " + defaults.getString("appinfo.config"))

  val file = new File(defaults.getString("appinfo.config"))

  val conf = ConfigFactory.parseFile(file).withFallback(fromSource).withFallback(defaults)

  val url = conf.getString("database_postgres.url")
  val username = conf.getString("database_postgres.username")
  val password = conf.getString("database_postgres.password")

  val kafka_url = conf.getString("kafka.url")
  val kafka_client_id = conf.getString("kafka.client-id")

  val kafka_schema = conf.getString("kafka.schema-registry")
  val kafka_batch_size = conf.getString("kafka.batch-size")

  val kafka_topic_trade = conf.getString("kafka.topic-name-trade")
  val kafka_topic_json = conf.getString("kafka.topic-name-json")

  println(s"url: $kafka_url,   client: $kafka_client_id")

}