package com.ruchij.exceptions

case class UndefinedEnvironmentValueException(envName: String) extends Exception
{
  override def getMessage: String = s""""$envName" is NOT defined in the environment."""
}
