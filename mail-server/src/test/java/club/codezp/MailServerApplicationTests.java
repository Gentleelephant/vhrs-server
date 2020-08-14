package club.codezp;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class MailServerApplicationTests {

    @Test
    void contextLoads() {
    }


//    @Autowired
//    private RabbitSender rabbitSender;
//
//    @Test
//    public void send() throws Exception{
//        Std std = new Std();
//        std.setId("123456789987");
//        std.setName("测试RabbitMQ");
//        std.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
//        rabbitSender.sendStd(std);
//    }

}
