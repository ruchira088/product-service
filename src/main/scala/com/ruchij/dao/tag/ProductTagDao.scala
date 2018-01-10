package com.ruchij.dao.tag

import com.ruchij.models.ProductTag

import scala.concurrent.Future

trait ProductTagDao
{
  def insert(productTag: ProductTag): Future[ProductTag]
}