package config

import com.typesafe.config.ConfigFactory

/**
The convenience method ConfigFactory.load() loads the following (first-listed are higher priority):

system properties
application.conf (all resources on classpath with this name)
application.json (all resources on classpath with this name)
application.properties (all resources on classpath with this name)
reference.conf (all resources on classpath with this name)
The idea is that libraries and frameworks should ship with a reference.conf in their jar.
Applications should provide an application.conf
, or if they want to create multiple configurations in a single JVM, they could use ConfigFactory.load("myapp") to load their own myapp.conf . (Applications can provide a reference.conf also if they want, but you may not find it necessary to separate it from application.conf .)
 */
object TypeSafeConfig {
  def main(args: Array[String]): Unit = {
    val config = ConfigFactory.load()   //will load application, reference config files by default.
    //will read from reference.conf due to application.conf does not contains database section (comments out)
    val schema = config.getString("database.url")
    val user = config.getString("database.user")
    val password = config.getString("database.password")

    // will read application.conf
    val email = config.getString("app.email")

    println(s"schema $schema")
    println(s"user $user")
    println(s"password $password")
    println(s"email $email")
  }
}
