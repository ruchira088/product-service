package com.ruchij.models

import org.joda.time.DateTime

case class ProductTag(id: String, createdAt: DateTime, label: String, description: Option[String])