import java.security.MessageDigest
import java.nio.charset.StandardCharsets
import scala.util.Using
import scala.concurrent.duration._

object HashBenchmark {
  def main(args: Array[String]): Unit = {
    if (args.length < 3) {
      println("Usage: scala HashBenchmark.sc <text> <numIterations> <algorithm>")
      System.exit(1)
    }

    val text = args(0)
    val numIterations = args(1).toInt
    val algorithm = args(2)

    val times = measureTimes(numIterations, text, algorithm)

    val percentiles = calculatePercentiles(times)

    println(s"$algorithm hash times percentiles:")
    percentiles.foreach(println)
  }

  private def measureTimes(iterations: Int, text: String, algorithm: String): Seq[Duration] = {
    (1 to iterations).map { _ =>
      measureTime(hashText(text, algorithm))
    }
  }

  private def measureTime(block: => Unit): Duration = {
    val start = System.nanoTime()
    block
    val end = System.nanoTime()
    Duration.fromNanos(end - start)
  }

  private def hashText(text: String, algorithm: String): Unit = {
    val messageDigest = MessageDigest.getInstance(algorithm)
    val _ = messageDigest.digest(text.getBytes(StandardCharsets.UTF_8))
  }

  private def calculatePercentiles(times: Seq[Duration]): Seq[String] = {
    val sortedTimes = times.sortBy(_.toNanos)
    val percentiles = List(50, 90, 95, 99)

    percentiles.map { percentile =>
      val index = (percentile.toDouble / 100 * times.size).toInt
      s"$percentile percentile: ${sortedTimes(index)}"
    }
  }
}
