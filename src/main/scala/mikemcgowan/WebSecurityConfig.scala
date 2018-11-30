package mikemcgowan

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler

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
        .logoutSuccessHandler(logoutSuccessHandler())
  }

  @Bean
  def logoutSuccessHandler(): LogoutSuccessHandler = {
    new CustomLogoutSuccessHandler()
  }

  @Autowired
  def configureGlobal(auth: AuthenticationManagerBuilder): Unit = {
    val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder
    val encodedPassword = encoder encode "Wibble123!"
    auth
      .inMemoryAuthentication
      .withUser("mike.mcgowan")
      .password(encodedPassword)
      .roles("USER")
    auth
      .inMemoryAuthentication
      .withUser("dave.smith")
      .password(encodedPassword)
      .roles("USER")
  }

}
