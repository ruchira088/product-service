package com.ruchij.dao

import scala.concurrent.Future
import scalaz.OptionT

trait ItemDao[T]
{
  def insert(item: T): Future[T]

  def update(id: String, newItem: T): Future[T]

  def getById(id: String): OptionT[Future, T]

  def delete(id: String): Future[T]
}