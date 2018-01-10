package com.ruchij.utils

import com.ruchij.exceptions.UndefinedEnvironmentValueException
import GeneralUtils._

import scala.util.Try

object ConfigUtils
{
  def getEnv(name: String): Option[String] = sys.env.get(name)

  def getEnvAsTry(name: String): Try[String] =
        toTry(getEnv(name), UndefinedEnvironmentValueException(name))

  def getEnvValues(names: String*): Try[List[String]] =
        flatten(names.toList.map(getEnvAsTry))
}
