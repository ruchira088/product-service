package com.ruchij.utils

object ConfigUtils
{
  def getEnv(name: String): Option[String] = sys.env.get(name)
}
