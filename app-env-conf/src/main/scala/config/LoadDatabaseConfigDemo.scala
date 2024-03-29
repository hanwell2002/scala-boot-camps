package config

import pureconfig._
import pureconfig.generic.auto._
object LoadDatabaseConfigDemo {

  case class DatabaseConfig(url: String, user: String, password: String)
  case class AppConfig(database: DatabaseConfig)

/*
  appName: "app-env-conf application"
  owner : "Hanwell"
  email: "javatar@outlook.com"
*/

  case class Appcfg(appname: String, owner: String, email: String)
  case class ApplicationConfig(appcfg: Appcfg)


  def main(args: Array[String]): Unit = {
    // config value will read from reference.conf which contains 'database" {blacks}
    // will load value in priority application ..., reference
    /**
     * system properties
     * application.conf (all resources on classpath with this name)
     * application.json (all resources on classpath with this name)
     * application.properties (all resources on classpath with this name)
     * reference.conf (all resources on classpath with this name)
     */
    ConfigSource.default.load[AppConfig] match {
      case Left(configReaderFailures) =>
        sys.error(s"Encountered the following errors reading the configuration: ${configReaderFailures.toList.mkString("\n")}")
      case Right(config) =>
        println(s"schema ${config.database.url}")
        println(s"user ${config.database.user}")
        println(s"password ${config.database.password}")
    }

    ConfigSource.default.load[ApplicationConfig] match {
      case Left(configReaderFailures) =>
        sys.error(s"Encountered the following errors reading the configuration: ${configReaderFailures.toList.mkString("\n")}")
      case Right(config) =>
        println(config)
        println(s"schema ${config.appcfg.owner}")
        println(s"user ${config.appcfg.email}")
        println(s"password ${config.appcfg.appname}")
    }



  }
}
