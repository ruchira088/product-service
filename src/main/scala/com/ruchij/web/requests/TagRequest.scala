package com.ruchij.web.requests

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.{DefaultJsonProtocol, RootJsonFormat}

case class TagRequest(label: String, description: Option[String])

object TagRequest extends DefaultJsonProtocol with SprayJsonSupport
{
  implicit def jsonFormat: RootJsonFormat[TagRequest] = jsonFormat2(TagRequest.apply)
}
