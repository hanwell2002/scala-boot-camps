//import Dependencies._
// import com.typesafe.sbt.packager.docker.DockerChmodType
// import com.typesafe.sbt.packager.docker.DockerPermissionStrategy
// import NativePackagerHelper._

ThisBuild / version := "1.0.1"

ThisBuild / scalaVersion := "2.12.15"

ThisBuild / assemblyMergeStrategy  := {
  case PathList("META-INF", _*) => MergeStrategy.discard
  case _ => MergeStrategy.first

//  case PathList("module-info.class") => MergeStrategy.discard
//  case x if x.endsWith("/module-info.class") => MergeStrategy.discard
//  case x =>
//    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
//    oldStrategy(x)
}
val sparkVersion = settingKey[String]("Spark version")

lazy val root = (project in file("."))
  .settings(
    name := "spark-starter",
    assembly / mainClass := Some("com.newhopebootcamps.spark.PostgresDataFrameDemo"),
  /*
  works:
  assembly / assemblyMergeStrategy := {
      case PathList("META-INF", _*) => MergeStrategy.discard
      case _ => MergeStrategy.first
    }*/
  )

enablePlugins(AssemblyPlugin)

/*
ThisBuild / assemblyMergeStrategy  := {
  case PathList("module-info.class") => MergeStrategy.discard
  case x if x.endsWith("/module-info.class") => MergeStrategy.discard
  case x =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}

assemblyMergeStrategy in assembly := {
  case "META-INF/io.netty.versions.properties" => MergeStrategy.first
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
*/

// enablePlugins(JavaAppPackaging, UniversalPlugin)
/*
enablePlugins(JavaAppPackaging, DockerPlugin)

Universal / mappings ++= directory("deployUtil")
Universal / mappings ++= directory("log")
Universal / mappings ++= directory("reports")
Universal / mappings ++= directory(sourceDirectory.value / "main" / "resources" / "config")
Universal / mappings += (sourceDirectory.value / "main" / "resources" / "log4j2.properties") -> "config/log4j2.properties"
Universal / mappings += (sourceDirectory.value / "main" / "resources" / "application.conf") -> "config/application.conf"
Universal / packageZipTarball / mappings += file("README") -> "README"
Universal / packageZipTarball / mappings += (baseDirectory.value / "release" / "release_1.0.1.sh") -> "release_1.0.1.sh"

Compile / mainClass := Some("com.newhopebootcamps.Main")
Docker / packageName := "hanwell/spark-starter"
dockerExposedPorts ++= Seq(5432, 8080, 9092)
dockerEnvVars ++= Map(("JAVA_HOME", "/opt/bootcamps/apps/CURR_JDK"), ("KAFKA_PORT", "9092"))
dockerExposedVolumes := Seq("/opt/docker/.logs", "/opt/docker/.keys", "/opt/bootcamps/reports", "/opt/bootcamps/logs")

defaultLinuxInstallLocation in Docker := "/opt/bootcamps"
dockerChmodType := DockerChmodType.UserGroupWriteExecute
dockerAdditionalPermissions += (DockerChmodType.UserGroupWriteExecute, "/opt/bootcamps/log")
dockerAdditionalPermissions += (DockerChmodType.UserGroupWriteExecute, "/opt/bootcamps/reports")
dockerPermissionStrategy := DockerPermissionStrategy.MultiStage
*/

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")
javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")
scalacOptions ++= Seq("-deprecation", "-unchecked")
parallelExecution in Test := false
fork := true
coverageHighlighting := true

lazy val _postgresJDBCVersion = "42.5.2"
lazy val _sparkVersion = "3.3.2"
lazy val _commons_codecVersion = "1.15"
lazy val _scala_csvVersion = "1.3.10"
lazy val _typesafeConfigVersion = "1.4.2"

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % _postgresJDBCVersion,
  "com.github.tototoshi" %% "scala-csv" % _scala_csvVersion,
  "com.typesafe" % "config" % _typesafeConfigVersion,
  "commons-codec" % "commons-codec" % _commons_codecVersion,
  "org.web3j" % "crypto" % "5.0.0",
  "org.scalacheck" %% "scalacheck" % "1.17.0" % "test",
  "org.scalatest" %% "scalatest" % "3.2.15" % Test,
  "org.scalatest" %% "scalatest-flatspec" % "3.2.15" % "test",
  "org.apache.hadoop" % "hadoop-client" % "3.3.4" % "provided",
  "org.apache.spark" %% "spark-core" % _sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % _sparkVersion % "provided",
  "org.apache.spark" %% "spark-mllib" % _sparkVersion % "provided",
  "org.apache.spark" %% "spark-streaming" % _sparkVersion % "provided",
  "com.holdenkarau" %% "spark-testing-base" % "3.3.0_1.3.0" % "test",
  "org.apache.logging.log4j" %% "log4j-api-scala" % "12.0",
  "org.apache.logging.log4j" % "log4j-api" % "2.20.0",
  "org.apache.logging.log4j" % "log4j-core" % "2.20.0" % Runtime,
)

// uses compile classpath for the run task, including "provided" jar (cf http://stackoverflow.com/a/21803413/3827)
run in Compile := Defaults.runTask(fullClasspath in Compile, mainClass in(Compile, run), runner in(Compile, run)).evaluated

// scalacOptions ++= Seq("-deprecation", "-unchecked")
pomIncludeRepository := { x => false }
resolvers ++= Seq(
  "sonatype-releases" at "https://oss.sonatype.org/content/repositories/releases/",
  "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/",
  "Second Typesafe repo" at "https://repo.typesafe.com/typesafe/maven-releases/",
  Resolver.sonatypeRepo("public")
)

pomIncludeRepository := { _ => false }

// publish settings
/*
publishTo := {
   val nexus = "https://oss.sonatype.org/"
   if (isSnapshot.value)
     Some("snapshots" at nexus + "content/repositories/snapshots")
   else
     Some("releases"  at nexus + "service/local/staging/deploy/maven2")
 }*/


//sbt "run C:/opt/bootcamps/data/people.txt C:/opt/bootcamps/data/peopleout.txt"
//------------------------------------notes---------------------------------------------
// sbt universal:packageZipTarball
// sbt universal:packageBin
// sbt universal:windows:packageBin
// sbt docker:stage    ---Generates a directory with the Dockerfile and environment prepared for creating a Docker image.
// docker:publishLocal   ---Builds an image using the local Docker server.
// docker:publish   ---Builds an image using the local Docker server, and pushes it to the configured remote repository.
// docker:clean  --Removes the built image from the local Docker server.
// docker build -t application-config-util .
// docker run -p 5432:5432 application-config-util
// RUN ["mkdir", "-p", "/opt/bootcamps/lib", "/opt/bootcamps/script", "/opt/bootcamps/config", "/opt/bootcamps/reports", "/opt/bootcamps/log"]

