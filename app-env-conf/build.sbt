ThisBuild / version := "1.0.1"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "app-env-conf"
  )

lazy val _postgresJDBCVersion = "42.5.1"
lazy val _slf4jApiVersion = "2.0.5"
lazy val _scalatestVersion = "3.2.14"
lazy val _logbackVersion = "1.2.3"
lazy val _scalikejdbcVersion = "4.0.0"
val specs2 = "org.specs2" %% "specs2-core" % "4.19.0" % "provided"

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % _postgresJDBCVersion,
  "ch.qos.logback" % "logback-classic" % _logbackVersion,
  "org.scalikejdbc" %% "scalikejdbc" % _scalikejdbcVersion,
  "org.scalikejdbc" %% "scalikejdbc-config" % _scalikejdbcVersion,
  "com.github.tototoshi" %% "scala-csv" % "1.3.10",
  // "com.github.melrief" %% "purecsv" % "0.1.1",
  "io.kontainers" %% "purecsv" % "1.3.10",
// "com.github.pureconfig" %% "pureconfig" % "0.9.1",
  "com.github.pureconfig" %% "pureconfig" % "0.17.2",
  "org.scalatest" %% "scalatest" % "3.2.14" % Test,
  "org.specs2" %% "specs2-core" % "4.19.0" % Test
)

enablePlugins(ScalikejdbcPlugin)



