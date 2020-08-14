package club.codezp.reveiver;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:Zpg
 * @Date:2020/7/11 14:46
 * @Version:1.0
 * @Description:
 */
//@Component
//public class RabbitSender {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    /**
//     * exchange: rabbitmq的exchange
//     * routingkey:
//     * 消息体内容
//     * correlationData: 消息的唯一id
//     * @param std
//     * @throws Exception
//     */
//    public void sendStd(Std std) throws Exception{
//
//        CorrelationData correlationData = new CorrelationData();
//        correlationData.setId(std.getMessageId());
//        rabbitTemplate.convertAndSend("std-exchange", "std.abc", std, correlationData);
//
//    }
//
//}
