package mikemcgowan

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}
import org.springframework.security.crypto.factory.PasswordEncoderFactories

@Configuration
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  override protected def configure(http: HttpSecurity): Unit = {
    http
      .authorizeRequests
        .antMatchers("/").permitAll
        .anyRequest.authenticated
        .and
      .formLogin
        .loginPage("/login").permitAll
        .and
      .logout
        .permitAll
  }

  @Autowired
  def configureGlobal(auth: AuthenticationManagerBuilder): Unit = {
    val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder
    val encodedPassword = encoder encode "pw"
    auth
      .inMemoryAuthentication
      .withUser("user")
      .password(encodedPassword)
      .roles("USER")
  }

}
