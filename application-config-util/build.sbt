import NativePackagerHelper._

ThisBuild / version := "1.0.1"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "application-config-util",
    assembly / mainClass := Some("com.newhopebootcamps.Main")
  )

//enablePlugins(AssemblyPlugin)
enablePlugins(JavaAppPackaging, UniversalPlugin)

Universal / mappings ++= directory("deployUtil")
Universal / mappings ++= directory(sourceDirectory.value / "main" / "resources" / "config")
Universal / mappings += (sourceDirectory.value / "main" / "resources"/ "logback.xml") -> "config/logback.xml"
Universal / mappings += (sourceDirectory.value / "main" / "resources"/ "database.cfg") -> "config/database2.cfg"
Universal / packageZipTarball / mappings += file("README") -> "README"
Universal / packageZipTarball / mappings += (baseDirectory.value / "release" / "release_1.0.1.sh") -> "release_1.0.1.sh"

// sbt universal:packageZipTarball
// sbt universal:packageBin
lazy val _postgresJDBCVersion = "42.5.1"

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % _postgresJDBCVersion,
  "com.github.tototoshi" %% "scala-csv" % "1.3.10",
  "com.typesafe" % "config" % "1.4.2",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "commons-codec" % "commons-codec" % "1.15",
  //"com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  //"com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
)

