import sbt._

object Dependencies
{
  lazy val akkaHttp =  "com.typesafe.akka" %% "akka-http" % "10.0.11"

  lazy val akkaStream =  "com.typesafe.akka" %% "akka-stream" % "2.5.8"

  lazy val akkaActor = "com.typesafe.akka" %% "akka-actor" % "2.5.8"

  lazy val akkaSprayJson = "com.typesafe.akka" %% "akka-http-spray-json" % "10.0.11"

  lazy val guice = "com.google.inject" % "guice" % "4.1.0"

  lazy val scalaz = "org.scalaz" %% "scalaz-core" % "7.2.18"

  lazy val jodaTime = "joda-time" % "joda-time" % "2.9.9"

  lazy val slick = "com.typesafe.slick" %% "slick" % "3.2.1"

  lazy val postgresDriver = "org.postgresql" % "postgresql" % "42.1.4"

  lazy val slickHikari =  "com.typesafe.slick" %% "slick-hikaricp" % "3.2.1"

  lazy val log4jBinding = "org.slf4j" % "log4j-over-slf4j" % "1.7.25"
}
