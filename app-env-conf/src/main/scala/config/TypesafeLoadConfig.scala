package config

import com.typesafe.config.{Config, ConfigFactory}
object TypesafeLoadConfig extends App {
  val applicationConf: Config = ConfigFactory.load("application.conf")

  val appname = applicationConf.getString("app.appName")
  val owner = applicationConf.getString("app.owner")
  val email = applicationConf.getString("app.email")

  print(appname)
  println(">> owner: " + owner)
  println(s" email: $email")

  //--------------------------------------------------------------
  val akkaConfig: Config = ConfigFactory.load("config/akka.conf")
  println(akkaConfig)
  println(akkaConfig.getString("akka.cluster.seed-node-timeout"))
  val akkacfgs = akkaConfig.getAnyRefList("akka.cluster.seed-nodes")  //.get(0)
  akkacfgs.forEach(println)
}