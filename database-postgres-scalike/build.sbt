ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "database-postgres-scalike"
  )
lazy val _slf4jApiVersion = "2.0.5"
lazy val _postgresJDBCVersion = "42.5.1"
//lazy val _logbackVersion = "1.4.5"   /*latest*/
lazy val _logbackVersion = "1.2.3"

libraryDependencies ++= Seq(
  "org.scalikejdbc" %% "scalikejdbc" % "4.0.0",
  "org.postgresql" % "postgresql" % _postgresJDBCVersion,
  // "org.slf4j" % "slf4j-api" % _slf4jApiVersion % "compile",
 //  "ch.qos.logback" % "logback-classic" % _logbackVersion % Test
  "ch.qos.logback" % "logback-classic" % _logbackVersion
)



