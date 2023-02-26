ThisBuild / version := "1.0.1"

ThisBuild / scalaVersion := "2.12.15"

lazy val root = (project in file("."))
   .settings(
   name := "application-testing"
)

lazy val _postgresJDBCVersion = "42.5.1"
lazy val _slf4jApiVersion = "2.0.5"
lazy val _logbackVersion = "1.2.3"

libraryDependencies ++= Seq (
  //"com.oracle.database.jdbc" % "ojdbc8" % "21.3.0.0",
  "org.postgresql" % "postgresql" % _postgresJDBCVersion,
  "com.github.tototoshi" %% "scala-csv" % "1.3.10",
  "com.typesafe" % "config" % "1.4.2",
  "ch.qos.logback" % "logback-classic" % _logbackVersion,
  "commons-codec" % "commons-codec" % "1.15",

  "org.scalatest" %% "scalatest" % "3.2.15" % Test,

  "org.scalatest" %% "scalatest-flatspec" % "3.2.15" % "test",
  "org.scalatest" %% "scalatest-featurespec" % "3.2.15" % "test",
  "org.scalatest" %% "scalatest-propspec" % "3.2.15" % "test",
  "org.scalatest" %% "scalatest-refspec" % "3.2.15" % "test"

)

