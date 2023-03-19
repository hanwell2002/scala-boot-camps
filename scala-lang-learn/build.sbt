ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "scala-lang-learn"
  )

lazy val _logbackVersion="1.2.3"
lazy val _commons_codecVersion= "1.15"
lazy val _scala_csvVersion="1.3.10"
lazy val _typesafeConfigVersion="1.4.2"

libraryDependencies ++= Seq(
  "com.github.tototoshi"          %% "scala-csv"                        % _scala_csvVersion,
  "com.typesafe"                  % "config"                            % _typesafeConfigVersion,
  "ch.qos.logback"                % "logback-classic"                   % _logbackVersion,
  "commons-codec"                 % "commons-codec"                     % _commons_codecVersion,
  "com.typesafe.slick"            %% "slick"                            % "3.4.1",
  "org.scalatest"                 %% "scalatest"                        % "3.2.15" % Test,
  "org.scalatest"                 %% "scalatest-flatspec" % "3.2.15"    % "test",
  "org.scalatest"                 %% "scalatest-featurespec" % "3.2.15" % "test",
  "org.scalatest"                 %% "scalatest-propspec" % "3.2.15"    % "test",
  "org.scalatest"                 %% "scalatest-refspec" % "3.2.15"     % "test"
)

