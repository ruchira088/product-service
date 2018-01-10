package com.ruchij.services

import javax.inject.Singleton

import com.ruchij.models.ProductItem
import com.ruchij.web.requests.ProductRequest

import scala.concurrent.Future

@Singleton
class ProductService
{
  def create(productRequest: ProductRequest): Future[ProductItem] = ???
}
