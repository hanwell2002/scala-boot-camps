import com.typesafe.sbt.packager.docker.DockerChmodType
import com.typesafe.sbt.packager.docker.DockerPermissionStrategy
import NativePackagerHelper._

ThisBuild / version := "1.0.1"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "application-config-util",
    assembly / mainClass := Some("com.newhopebootcamps.Main")
  )

// enablePlugins(AssemblyPlugin)
//enablePlugins(JavaAppPackaging, UniversalPlugin)
//enablePlugins(JavaAppPackaging,DockerPlugin,  WindowsPlugin)  //DockerSpotifyClientPlugin
enablePlugins(JavaAppPackaging, DockerPlugin)

//--------------for Windows plugins ------------------------
// enablePlugins(WindowsPlugin)
// general package information (can be scoped to Windows)
/*
maintainer := "Josh Suereth <joshua.suereth@typesafe.com>"
packageSummary := "test-windows"
packageDescription := """Test Windows MSI."""

// wix build information
wixProductId := "ce07be71-510d-414a-92d4-dff47631848a"
wixProductUpgradeId := "4552fb0e-e257-4dbd-9ecb-dba9dbacf424"
//--------------end of windows plugins----------------
*/

Universal / mappings ++= directory("deployUtil")
Universal / mappings ++= directory("log")
Universal / mappings ++= directory("reports")
Universal / mappings ++= directory(sourceDirectory.value / "main" / "resources" / "config")
Universal / mappings += (sourceDirectory.value / "main" / "resources"/ "logback.xml") -> "config/logback.xml"
// Universal / mappings += (sourceDirectory.value / "main" / "resources"/ "config/database.cfg") -> "config/database.cfg"
Universal / packageZipTarball / mappings += file("README") -> "README"
Universal / packageZipTarball / mappings += (baseDirectory.value / "release" / "release_1.0.1.sh") -> "release_1.0.1.sh"

/*
Windows / mappings ++= directory("log")
Windows / mappings ++= directory("reports")
Windows / mappings ++= directory(sourceDirectory.value / "main" / "resources" / "config")
*/

lazy val _postgresJDBCVersion = "42.5.1"

Compile / mainClass := Some("com.newhopebootcamps.Main")
Docker / packageName := "arceed/application-config-util"
dockerExposedPorts ++= Seq(5432, 8080, 9000)
dockerEnvVars ++= Map(("COCKROACH_HOST", "dev.localhost"), ("COCKROACH_PORT", "26257"))
dockerExposedVolumes := Seq("/opt/docker/.logs", "/opt/docker/.keys")

defaultLinuxInstallLocation in Docker := "/opt/bootcamps"
dockerChmodType := DockerChmodType.UserGroupWriteExecute
dockerAdditionalPermissions += (DockerChmodType.UserGroupPlusExecute, "/opt/docker/bin/hello")
dockerPermissionStrategy := DockerPermissionStrategy.None

libraryDependencies ++= Seq(
  "org.postgresql"                % "postgresql"                      % _postgresJDBCVersion,
  "com.github.tototoshi"          %% "scala-csv"                      % "1.3.10",
  "com.typesafe"                  % "config"                          % "1.4.2",
  "ch.qos.logback"                % "logback-classic"                 % "1.2.3",
  "commons-codec"                 % "commons-codec"                   % "20041127.091804",
  //"com.spotify"                   % "docker-client"                   % "8.9.0", //no good slow build, conflict with assembly
  "org.web3j"                     % "crypto"                          % "5.0.0",
  "com.typesafe.slick"            %% "slick"                          % "3.4.1",
  "org.scalatest"                 % "scalatest_2.11"                  % "3.2.15"
)

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

