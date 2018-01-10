package com.ruchij.utils

import java.util.UUID

import com.ruchij.exceptions.EmptyOptionException

import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

object GeneralUtils
{
  def uuid(): String = UUID.randomUUID().toString

  def flatten[T](tryList: List[Try[T]]): Try[List[T]] =
    if (tryList.isEmpty)
      Success(List.empty)
    else
      for {
        rest <- flatten(tryList.tail)
        current <- tryList.head
      }
      yield current :: rest

  def toTry[T](option: Option[T], exception: => Exception = EmptyOptionException): Try[T] =
    option.fold[Try[T]](Failure(exception))(Success(_))

  def convert[A, B](value: A, converter: A => B): Try[B] =
    try {
      Success(converter(value))
    } catch {
      case NonFatal(throwable) => Failure(throwable)
    }
}
