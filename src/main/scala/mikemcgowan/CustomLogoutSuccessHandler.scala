package mikemcgowan

import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.logout.{LogoutSuccessHandler, SimpleUrlLogoutSuccessHandler}

class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler with LogoutSuccessHandler {

  @Autowired
  private val eventPublisher: ApplicationEventPublisher = null

  override def onLogoutSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication): Unit = {
    val user = authentication.getPrincipal.asInstanceOf[User]
    val logout = Logout(user.getUsername)
    eventPublisher publishEvent logout
    super.onLogoutSuccess(request, response, authentication)
  }

}
