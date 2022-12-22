package config

/*
import pureconfig.ConfigSource
import pureconfig._
import pureconfig.generic.auto._
*/

import pureconfig._
import pureconfig.generic.auto._

case class Port(number: Int) extends AnyVal
sealed trait AuthMethod
case class Login(username: String, password: String) extends AuthMethod
case class Token(token: String) extends AuthMethod
case class PrivateKey(pkFile: java.io.File) extends AuthMethod
case class ServiceConf(
                        host: String,
                        port: Port,
                        useHttps: Boolean,
                        authMethods: List[AuthMethod]
                      )

object Configuration {

  val serviceConf: ServiceConf = ConfigSource.default.load[ServiceConf] match {
    case Right(conf) => conf
    case Left(error) => throw new Exception(error.toString())
  }

}