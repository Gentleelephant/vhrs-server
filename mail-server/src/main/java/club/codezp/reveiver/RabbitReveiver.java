package club.codezp.reveiver;

import com.rabbitmq.client.Channel;
import org.mockito.internal.matchers.Or;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author:Zpg
 * @Date:2020/7/11 16:37
 * @Version:1.0
 * @Description:
 */
//@Component
//public class RabbitReveiver {
//
//    /**
//     *
//     * 如果知道消息是什么类型就可以用@Payload把参数定义成什么类型
//     * @param headers 定义消息头
//     * @param std
//     * @param channel：手工签收方式要依赖于Channel。
//     */
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue(value = "std-queue", durable = "true"),
//            exchange = @Exchange(name = "std-exchange",durable = "true",type = "topic"),
//            key = "std.*")
//            )
//    @RabbitHandler // 标识,但并没有绑定,要用RabbitListener绑定
//    public void reveiverStdMessage(@Payload Std std,
//                                   @Headers Map<String,Object> headers,
//                                   Channel channel)throws Exception{
//        // 消费者操作
//        System.out.println("------------------收到消息,打印ID----------------");
//        System.out.println("ID：" + std.getId());
//
//        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
//        // 手工签收必须要确认
//        // false表示不支持批量签收
//        channel.basicAck(deliveryTag, false);
//    }
//
//}
