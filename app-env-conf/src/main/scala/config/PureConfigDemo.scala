package config

object PureConfigDemo extends App {
  val config = Configuration.serviceConf
  println("Application configs:::: " + config)
}