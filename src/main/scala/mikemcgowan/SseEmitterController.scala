package mikemcgowan

import java.util
import java.util.concurrent.CopyOnWriteArrayList

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter

@Controller
class SseEmitterController {

  private val emitters = new CopyOnWriteArrayList[SseEmitter]

  @GetMapping(Array("/sse"))
  def handle(): SseEmitter = {
    val emitter = new SseEmitter
    emitters add emitter
    emitter onCompletion (() => emitters.remove(emitter))
    emitter onTimeout (() => emitters.remove(emitter))
    emitter
  }

  @EventListener
  def onMemoryInfo(memoryInfo: MemoryInfo): Unit = {
    val deadEmitters = new util.ArrayList[SseEmitter]
    emitters forEach (emitter => {
      try {
        emitter send memoryInfo.toJsonString
      } catch {
        case _: Exception => deadEmitters add emitter
      }
    })
    emitters removeAll deadEmitters
  }

}
