package com.ruchij.services

import javax.inject.{Inject, Singleton}

import com.ruchij.dao.tag.ProductTagDao
import com.ruchij.models.ProductTag
import com.ruchij.web.requests.TagRequest
import org.joda.time.DateTime

import scala.concurrent.Future

@Singleton
class TagService @Inject()(productTagDao: ProductTagDao)
{
  import TagService._

  def create(tagRequest: TagRequest): Future[ProductTag] =
    productTagDao.insert(productTag(tagRequest))
}

object TagService
{
  def tagId(label: String): String = label.toLowerCase.filter(_.isLetterOrDigit)

  def productTag(tagRequest: TagRequest): ProductTag =
    ProductTag(tagId(tagRequest.label), DateTime.now(), tagRequest.label, tagRequest.description)
}
