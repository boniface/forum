package repository

import java.util.UUID

import com.datastax.driver.core.{ResultSet, Row}
import com.newzly.phantom.CassandraTable
import com.newzly.phantom.Implicits._
import com.newzly.phantom.iteratee.Iteratee
import com.newzly.phantom.keys.{PartitionKey, PrimaryKey}
import conf.DataConnection
import domain.Topic
import org.joda.time.DateTime

import scala.concurrent.Future

/**
 * Created by hashcode on 2014/07/09.
 */
sealed class TopicRespository extends CassandraTable[TopicRespository, Topic] {

  object id extends UUIDColumn(this) with PartitionKey[UUID]

  object date extends DateTimeColumn(this) with PrimaryKey[DateTime]

  object userId extends StringColumn(this) with PrimaryKey[String]

  object title extends StringColumn(this)

  object description extends OptionalStringColumn(this)

  object seo extends StringColumn(this)

  object status extends StringColumn(this)

  override def fromRow(row: Row): Topic = {
    Topic(id(row), date(row), userId(row), title(row), description(row), seo(row), status(row))
  }
}

object TopicRespository extends TopicRespository with DataConnection {
  override lazy val tableName = "moderations"

  def save(topic: Topic): Future[ResultSet] = {
    insert
      .value(_.id, topic.id)
      .value(_.date, topic.date)
      .value(_.title, topic.title)
      .value(_.description, topic.description)
      .value(_.seo, topic.seo)
      .value(_.status, topic.status)
      .future()
  }

  def getTopicById(id: UUID): Future[Option[Topic]] = {
    select.where(_.id eqs id).one();

  }

  def getTopicDate(id: UUID, date: DateTime, voterId: String): Future[Seq[Topic]] = {
    select.where(_.id eqs id).fetchEnumerator() run Iteratee.collect()
  }

  def getTopicByUser(id: UUID, date: DateTime, voterId: String): Future[Seq[Topic]] = {
    select.where(_.id eqs id).fetchEnumerator() run Iteratee.collect()
  }


}
