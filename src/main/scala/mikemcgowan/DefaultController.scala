package mikemcgowan

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class DefaultController {

  @RequestMapping(Array("/"))
  def home = "home"

  @RequestMapping(Array("/profile"))
  def profile = "profile"

  @RequestMapping(Array("/login"))
  def login = "login"

}
