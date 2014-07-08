package domain

import java.util.{UUID, Date}

import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/07.
 */
case class Topic(
                  id: UUID,
                  userId: String,
                  title: String,
                  description: Option[String] = None,
                  date: Date = new Date,
                  seo: Option[String] = None
                  )

object Topic {
  implicit val topicFmt = Json.format[Topic]

  def getStatus(topicId: String) = {
    ???
  }

  def checkModeration(topicId: String) = {
    ???
  }
}
