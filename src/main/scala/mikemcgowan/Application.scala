package mikemcgowan

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class Application

object Application {

  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[Application], args: _*)
  }

}
