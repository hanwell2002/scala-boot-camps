package utility.config

import com.typesafe.config.ConfigFactory
import utility.encryption.EncryptionUtil

import java.io.File
import scala.util.control.ControlThrowable

object ApplicationConfiguration {
  var url: String = ""
  var username: String = ""
  var password: String = ""
  var key: String = ""
  var initVector: String = ""

  var kafka_client_id = ""
  var kafka_url = ""

  var kafka_schema = ""
  var kafka_topic_trade = ""
  var kafka_topic_json = ""
  var kafka_batch_size = ""

  private val loaded = initialize

  def initialize(): Boolean = {
    try {
      val defaults = ConfigFactory.load()
      val fromSource = ConfigFactory.parseResources("kafka.conf").withFallback(defaults)
      val file = new File(defaults.getString("appinfo.config"))
      val conf = ConfigFactory.parseFile(file).withFallback(fromSource).withFallback(defaults)

      key = conf.getString("secure.key")
      initVector = conf.getString("secure.initVector")
      println(s"key=$key, initVector = $initVector")

      url = conf.getString("database_postgres.url")
      username = conf.getString("database_postgres.username")
      password = EncryptionUtil.decrypt(conf.getString("database_postgres.password"))
      println(s"Initialize: url=$url, username=$username, pwd = $password")

      kafka_url = conf.getString("kafka.url")
      kafka_client_id = conf.getString("kafka.client-id")

      kafka_schema = conf.getString("kafka.schema-registry")
      kafka_topic_trade = conf.getString("kafka.topic-name-trade")
      kafka_topic_json = conf.getString("kafka.topic-name-json")
      kafka_batch_size = conf.getString("kafka.batch-size")


      val appName = conf.getString("appinfo.name")
      println(s"appName=$appName")

      /*    println(conf.getString("Modules.Logging.logDb"))
            println(conf.getString("Modules.Tenants.tenantsDb"))
            println(conf.getString("KafkaClient.principal"))*/

    } catch safely {
      case ex: Throwable => println("ERROR: Failed to initialize application config!")
        // case _: Throwable => logger.error("Exception: Failed to initialize application config!!")
        false
    }
    true
  }

  def safely[T](handler: PartialFunction[Throwable, T]): PartialFunction[Throwable, T] = {
    case ex: ControlThrowable => throw ex
    case ex: Throwable if handler.isDefinedAt(ex) => handler(ex)
    // If they didn't handle it, rethrow. This line isn't necessary, just for clarity
    case ex: Throwable => throw ex
  }

}

