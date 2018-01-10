package com.ruchij.modules

import akka.actor.ActorSystem
import com.google.inject.AbstractModule
import com.ruchij.dao.tag.{ProductTagDao, SlickProductTagDao}
import slick.jdbc.PostgresProfile

import scala.concurrent.ExecutionContext

class GuiceModule(implicit actorSystem: ActorSystem, executionContext: ExecutionContext) extends AbstractModule
{
  override def configure() =
  {
    bind(classOf[ExecutionContext]).toInstance(executionContext)

    bind(classOf[ProductTagDao]).to(classOf[SlickProductTagDao])

    bind(classOf[PostgresProfile.backend.Database]).toInstance(PostgresProfile.backend.Database.forConfig("database"))
  }
}

object GuiceModule
{
  def apply()(implicit actorSystem: ActorSystem, executionContext: ExecutionContext): GuiceModule = new GuiceModule()
}