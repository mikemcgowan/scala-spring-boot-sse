package mikemcgowan

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class DefaultController {

  @RequestMapping(Array("/"))
  def home(): String = "home"

  @RequestMapping(Array("/profile"))
  def profile(): String = "profile"

  @RequestMapping(Array("/login"))
  def login(): String = "login"

}
