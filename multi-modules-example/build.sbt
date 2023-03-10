import Dependencies._
// import NativePackagerHelper._

ThisBuild / scalaVersion := "2.12.15"
ThisBuild / version := "1.0"
ThisBuild / organization := "com.newhopebootcamps"
ThisBuild / organizationName := "newhopebootcamps"

ThisBuild / assemblyMergeStrategy := {
  case PathList("META-INF", _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

lazy val hello = taskKey[Unit]("An hello task")
lazy val taskDemo = taskKey[Unit]("Just print out a photo")

taskDemo := {
  println("Task demo!")
  for (line <- scala.io.Source.fromFile("/opt/bootcamps/data/photo.txt").getLines) {
    println(line)
  }
}

lazy val commonSettings = Seq(
  target := {
    baseDirectory.value / "target"
  },
  libraryDependencies ++= Seq(
    "ch.qos.logback" % "logback-classic" % _logbackVersion,
    "org.apache.kafka" %% "kafka" % "3.4.0",
    "org.apache.kafka" % "kafka-clients" % "3.4.0" % "provided",
  ),
  scalacOptions ++= Seq("-deprecation")
)

lazy val appcommons = (project in file("appcommons"))
  .settings(commonSettings)
  .settings(
    name := "appcommons module",
    libraryDependencies ++= Seq(
//  "ch.qos.logback" % "logback-classic" % _logbackVersion,
    )
  )
lazy val utility = (project in file("scm-utility"))
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % _typesafeConfigVersion
    ),
    scalacOptions --= Seq(
      "-Xfatal-warnings"
    )
  )

lazy val producer = (project in file("producer"))
  .dependsOn(appcommons, utility)
  .settings(commonSettings)
  .settings(
    name := "producer-module",
    libraryDependencies ++= Seq(
      "com.google.code.gson" % "gson" % "2.10.1",
    ),
    assembly / mainClass := Some("com.newhopebootcamps.stream.KafkaScalaProducer")
  )

lazy val consumer = (project in file("consumer"))
  .dependsOn(appcommons, utility)
  .settings(commonSettings)
  .settings(
    name := "consumer-module",
    libraryDependencies ++= Seq(
      "org.apache.kafka" %% "kafka" % "3.4.0",
      "org.apache.kafka" % "kafka-clients" % "3.4.0" % "provided"
    ),
    assembly / mainClass := Some("com.newhopebootcamps.stream.KafkaScalaConsumer")
  )

lazy val root = (project in file("."))
  .dependsOn(appcommons, utility, consumer, producer)
  .aggregate(appcommons, producer, consumer)
  .settings(
    name := "multi-modules-example",

    hello := {
      println("Hello! Task example")  //scalac Name.class
    },

    assembly / assemblyJarName := "multi-module-assembly-fatjar-1.0.jar",
    assembly / mainClass := Some("com.newhopebootcamps.application.Main"),
  )

lazy val _postgresJDBCVersion = "42.5.1"
lazy val _logbackVersion = "1.2.3"
lazy val _commons_codecVersion = "1.15"
lazy val _scala_csvVersion = "1.3.10"
lazy val _typesafeConfigVersion = "1.4.2"

libraryDependencies ++= Seq(
  "commons-codec" % "commons-codec" % _commons_codecVersion,
  "com.github.tototoshi" %% "scala-csv" % _scala_csvVersion,
  "com.typesafe.slick" %% "slick" % "3.4.1",
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.15" % "test",
)

scalacOptions ++= Seq("-deprecation")

// enablePlugins(AssemblyPlugin)
enablePlugins(JavaAppPackaging, UniversalPlugin)

assembly / mainClass := Some("com.newhopebootcamps.application.Main")

// sbt universal:packageZipTarball
// sbt universal:packageBin

// java -cp multi-modules-example.jar com.newhopebootcamps.application.Main Producer Consumer
// java -jar target/scala-2.12/multi-module-assembly-fatjar-1.0.jar com.newhopebootcamps.application.Main Producer
// - open another Terminal run:
// java -jar target/scala-2.12/multi-module-assembly-fatjar-1.0.jar com.newhopebootcamps.application.Main Consumer

