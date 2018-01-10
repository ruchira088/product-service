package com.ruchij.utils

import java.util.UUID

import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

object GeneralUtils
{
  def uuid(): String = UUID.randomUUID().toString

  def convert[A, B](value: A, converter: A => B): Try[B] =
    try {
      Success(converter(value))
    } catch {
      case NonFatal(throwable) => Failure(throwable)
    }
}
