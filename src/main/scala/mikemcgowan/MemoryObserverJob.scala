package mikemcgowan

import java.lang.management.ManagementFactory

import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class MemoryObserverJob(val eventPublisher: ApplicationEventPublisher) {

  @Scheduled(fixedRate = 1000)
  def doSomething(): Unit = {
    val memBean = ManagementFactory.getMemoryMXBean
    val heap = memBean.getHeapMemoryUsage
    val nonHeap = memBean.getNonHeapMemoryUsage

    val mi = MemoryInfo(heap.getUsed, nonHeap.getUsed)
    eventPublisher publishEvent mi
  }

}
