package club.codezp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MailServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailServerApplication.class, args);
    }

//    @Bean
//    Queue queue(){
//        return new Queue("aboy.mail.welcome");
//    }
//

}
