import scalaz._
import Scalaz._
import scala.concurrent.Await

object Main extends App {

  import dispatch._, Defaults._
  import scala.concurrent.duration._

  val endpoint = "http://stream-sandbox.oanda.com/v1/prices?instruments=USD_JPY"
  val request = Http(url(endpoint) > as.stream.Lines(printer))

  def printer(line: String) = line match {
    case l if l.contains("heartbeat") => // nothing to do.
    case l => println(l)
  }

  Await.result(request, Duration.Inf)

}

