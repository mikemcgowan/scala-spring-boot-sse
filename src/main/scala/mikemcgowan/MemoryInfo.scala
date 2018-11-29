package mikemcgowan

import java.util.Date

case class MemoryInfo(heap: Long, nonHeap: Long, ts: Date = new Date()) {

  def toJsonString: String =
    "{\"ts\": \"%s\", \"heap\": %s, \"nonHeap\": %s}" format (
      ts.toString,
      heap,
      nonHeap
    )

}
