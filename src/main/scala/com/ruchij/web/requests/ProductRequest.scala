package com.ruchij.web.requests

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class ProductRequest(id: String, name: String, title: Option[String], price: BigDecimal, tags: List[String])

object ProductRequest extends DefaultJsonProtocol with SprayJsonSupport
{
  implicit def jsonFormat: RootJsonFormat[ProductRequest] = jsonFormat5(ProductRequest.apply)
}
