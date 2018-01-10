package com.ruchij.dao.tag

import java.sql.Timestamp
import javax.inject.{Inject, Singleton}

import com.ruchij.models.ProductTag
import org.joda.time.DateTime
import slick.ast.BaseTypedType
import slick.jdbc.PostgresProfile._
import slick.jdbc.meta.MTable
import slick.jdbc.{JdbcType, PostgresProfile}

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._

@Singleton
class SlickProductTagDao @Inject()(db: PostgresProfile.backend.Database)(implicit executionContext: ExecutionContext)
  extends ProductTagDao
{
  import SlickProductTagDao._
  import api._

  class ProductTagTable(tag: Tag) extends Table[ProductTag](tag, TABLE_NAME)
  {
    implicit def dateColumnType: JdbcType[DateTime] with BaseTypedType[DateTime] =
      MappedColumnType.base[DateTime, Timestamp](
        dateTime => new Timestamp(dateTime.getMillis),
        timeStamp => new DateTime(timeStamp.getTime)
      )

    def id = column[String]("id")
    def createdAt = column[DateTime]("created_at")
    def label = column[String]("label")
    def description = column[Option[String]]("description")

    override def * = (id, createdAt, label, description) <> ((ProductTag.apply _).tupled, ProductTag.unapply)
  }

  val productTags = TableQuery[ProductTagTable]

  def insert(productTag: ProductTag): Future[ProductTag] =
    for {
      _ <- db.run(productTags += productTag)
    }
    yield productTag

  def createSchema(): Future[Unit] =
    for {
      tables <- db.run(MTable.getTables)
      result <- if (tables.exists(_.name.name == TABLE_NAME)) Future.successful() else db.run(productTags.schema.create)
    }
    yield result

  Await.ready(createSchema(), 30 seconds)
}

object SlickProductTagDao
{
  val TABLE_NAME = "products"
}
