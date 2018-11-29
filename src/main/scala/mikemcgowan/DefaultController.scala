package mikemcgowan

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class DefaultController {

  @RequestMapping(Array("/"))
  def home(): String = "home"

  @RequestMapping(Array("/monitor"))
  def monitor(): String = "monitor"

  @RequestMapping(Array("/login"))
  def login(): String = "login"

}
