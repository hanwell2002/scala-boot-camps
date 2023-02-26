package config

import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory

import java.io.File
//import scala.collection.JavaConversions._
import scala.collection.immutable.Map

object TypesafeLoadConfig extends App {

  val logger = LoggerFactory.getLogger(this.getClass)
  logger.info("*** Application started ......")

  val cfgPath = "C:/var/config/application.conf"
  val resourcePath = "KafkaConnection2.properties"
  val absFilePath = "C:/var/config/KafkaConnection.properties"
  val absFilePathWindows = "C:\\var\\config\\KafkaConnection.properties"

  //demo01("application.conf")
  demo01(cfgPath)
  // works
  // loadProperties (resourcePath)
  loadProperties(absFilePath)

  def demo01(configPath: String): Unit = {
    // good: val applicationConf: Config = ConfigFactory.load("application.conf")
    // bad:  val applicationConf: Config = ConfigFactory.load("C:/var/config/application.conf")
    // val applicationConf: Config = ConfigFactory.load(configPath)
    val defaults = ConfigFactory.load()
    val file = new File(configPath)
    // but you could read environment variables into a map and then use
    var envMap: Map[String, String] = sys.env.toMap
    envMap.foreach{
      pair=>
        println(s"${pair._1} : ${pair._2}")
    }
    // for (pair <- envMap) println(s"${pair._1} : ${pair._2}")
/*    envMap.foreach{
        case (key, value) =>
          println(s"key=$key,  value=$value")
      } */

    // val env = ConfigFactory.parseMap(envMap)
    // val settings = ConfigFactory.parseFile(file).withFallBack(env).withFallBack(defaults)
    val conf = ConfigFactory.parseFile(file).withFallback(defaults) //.withFallback(env)
    val appname = conf.getString("app.appName")
    val owner = conf.getString("app.owner")
    val email = conf.getString("app.email")
    val use_https = conf.getString("use-https")
    logger.info(">> use-https: " + use_https)
    logger.info(s"m01: appname=$appname, owner=$owner, email: $email")
  }

  def demo02(): Unit = {
    val akkaConfig: Config = ConfigFactory.load("config/akka.conf")
    logger.info(akkaConfig.toString)
    logger.info(akkaConfig.getString("akka.cluster.seed-node-timeout"))
    val akkacfgs = akkaConfig.getAnyRefList("akka.cluster.seed-nodes") //.get(0)
    akkacfgs.forEach(println)
  }

  def loadProperties(configPath: String): Unit = {
    // val applicationConf: Config = ConfigFactory.load(configPath)
    val defaults = ConfigFactory.load() // will load application, reference etc.
    val file = new File(configPath)
    val settings = ConfigFactory.parseFile(file).withFallback(defaults)
    val host = settings.getString("host")
    val port = settings.getString("port")
    val schema = settings.getString("schema")

    logger.info(s"m02: host=$host, port=$port, schema: $schema")
    val url = settings.getString("db_config.url")
    logger.info("m02: url--> " + url)

    val logDb = settings.getString("Modules.Logging.logDb")
    logger.info(logDb)
    val tenantsDb = settings.getString("Modules.Tenants.tenantsDb")
    logger.info(tenantsDb)

  }

}