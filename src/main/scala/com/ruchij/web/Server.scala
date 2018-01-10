package com.ruchij.web

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.google.inject.Guice
import com.ruchij.constants.{ConfigValues, EnvValueNames}
import com.ruchij.modules.GuiceModule
import com.ruchij.services.TagService
import com.ruchij.utils.ConfigUtils.getEnv
import com.ruchij.utils.GeneralUtils
import com.ruchij.web.requests.{ProductRequest, TagRequest}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Promise}
import scala.util.{Failure, Success}

object Server
{
  def main(args: Array[String]): Unit =
  {
    implicit val actorSystem = ActorSystem("product-service")
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = actorSystem.dispatcher

    val injectors = Guice.createInjector(GuiceModule())

    val tagService = injectors.getInstance(classOf[TagService])

    val route =
      path("product") {
        post {
          entity(as[ProductRequest]) {
            productRequest => ???
          }
        }
      } ~
      path("product-tag") {
        post {
          entity(as[TagRequest]) {
            tagRequest => onComplete(tagService.create(tagRequest)) {

              case Success(productTag) => complete(StatusCodes.Created, productTag.toString)

              case Failure(throwable) => complete(throwable.getMessage)
            }
          }
        }
      }

    Http().bindAndHandle(route, "0.0.0.0", getHttpPort())
      .andThen {
        case Success(server) => println(s"Server is listening om port ${server.localAddress.getPort}...")
        case Failure(throwable) => throw throwable
      }

    Await.ready(Promise[Unit].future, Duration.Inf)
  }

  def getHttpPort(): Int =
    getEnv(EnvValueNames.HTTP_PORT)
      .flatMap(GeneralUtils.convert[String, Int](_, _.toInt ).toOption)
      .getOrElse(ConfigValues.DEFAULT_HTTP_PORT)
}
