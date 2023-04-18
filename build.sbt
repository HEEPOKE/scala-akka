name := "akka-quickstart-scala"

version := "1.0"

scalaVersion := "2.13.10"

lazy val akkaVersion = "2.8.0"

fork := true

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % "10.2.6",
  "com.typesafe.akka" %% "akka-stream" % "2.6.17",
  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.scalatest" %% "scalatest" % "3.1.0" % Test,
  "com.typesafe" % "config" % "1.4.1",
  "org.postgresql" % "postgresql" % "42.2.23"
)
