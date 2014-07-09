package domain

import java.util.{UUID, Date}

import org.joda.time.DateTime
import play.api.libs.json.Json

/**
 * Created by hashcode on 2014/07/07.
 */
case class Topic(
                  id: UUID,
                  date: DateTime = new DateTime,
                  userId: String,
                  title: String,
                  description: Option[String],
                  seo: String,
                  status:String
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
