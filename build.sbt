import Dependencies._

lazy val root = (project in file("."))
  .settings(
    inThisBuild(List(
      organization := "com.ruchij",
      scalaVersion := "2.12.4",
      version      := "0.1.0"
    )),
    name := "product-service",
    libraryDependencies ++= Seq(akkaHttp, akkaStream, akkaActor, akkaSprayJson),
    libraryDependencies += guice,
    libraryDependencies += scalaz,
    libraryDependencies += jodaTime,
    libraryDependencies ++= Seq(slick, postgresDriver, slickHikari),
    libraryDependencies += log4jBinding
  )
