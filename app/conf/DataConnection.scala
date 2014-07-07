package conf





import scala.concurrent._

import com.datastax.driver.core.{Session, Cluster}
import com.newzly.phantom.Implicits._
import scala.concurrent. { blocking, Future }
/**
 * Created by hashcode on 2014/07/07.
 */
object DataConnection {
  val keySpace = "ngforum"

  lazy val cluster = Cluster.builder()
    .addContactPoint("localhost")
    .withPort(9042)
    .withoutJMXReporting()
    .withoutMetrics()
    .build()

  lazy val session = blocking {
    cluster.connect(keySpace)
  }
}

trait DataConnection {
  self: CassandraTable[_, _] =>

  def createTable(): Future[Unit] ={
    create.future() map (_ => ())
  }

  implicit lazy val datastax: Session = DataConnection.session
}