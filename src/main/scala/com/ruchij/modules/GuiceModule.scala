package com.ruchij.modules

import akka.actor.ActorSystem
import com.google.inject.AbstractModule
import com.ruchij.constants.EnvValueNames._
import com.ruchij.dao.tag.{ProductTagDao, SlickProductTagDao}
import com.ruchij.utils.ConfigUtils.getEnvValues
import slick.jdbc.PostgresProfile

import scala.concurrent.ExecutionContext
import scala.util.Try

class GuiceModule(implicit actorSystem: ActorSystem, executionContext: ExecutionContext) extends AbstractModule
{
  import GuiceModule._

  override def configure() =
  {
    bind(classOf[ExecutionContext]).toInstance(executionContext)

    bind(classOf[ProductTagDao]).to(classOf[SlickProductTagDao])

    getDatabaseConfig().fold(throw _, bindDatabase)
  }

  def bindDatabase(databaseConfig: DatabaseConfig) =
  {
    bind(classOf[PostgresProfile.backend.Database])
      .toInstance(
        PostgresProfile.backend.Database.forURL(
          databaseConfig.url,
          databaseConfig.user,
          databaseConfig.password
        )
      )
  }
}

object GuiceModule
{
  case class DatabaseConfig(url: String, user: String, password: String)

  def apply()(implicit actorSystem: ActorSystem, executionContext: ExecutionContext): GuiceModule =
    new GuiceModule()

  def getDatabaseConfig(): Try[DatabaseConfig] =
    for {
      List(databaseUrl, databaseUser, databasePassword) <- getEnvValues(POSTGRES_URL, POSTGRES_USER, POSTGRES_PASSWORD)
    }
    yield DatabaseConfig(databaseUrl, databaseUser, databasePassword)
}