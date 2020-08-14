package club.codezp.reveiver;

import com.zhang.model.Employee;
import com.zhang.model.Hr;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import java.util.Date;

/**
 * @Author:Zpg
 * @Date:2020/7/10 17:59
 * @Version:1.0
 * @Description: 邮件发送
 */
@Component
public class MailReceiver {


    public static final Logger logger = LoggerFactory.getLogger(MailReceiver.class);


    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    MailProperties mailProperties;

    @Autowired
    TemplateEngine templateEngine;


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "mail-welcome", durable = "true"),
            exchange = @Exchange(name = "my-mail",durable = "true",type = "topic"),
            key = "mail.*"))
    @RabbitHandler
    public void handler(Employee employee){
        logger.info(employee.toString());
        // 收到消息，发送邮件
        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg);
        try {
            helper.setTo(employee.getEmail());
            helper.setFrom(mailProperties.getUsername());
            helper.setSubject("入职欢迎");
            helper.setSentDate(new Date());
            org.thymeleaf.context.Context context = new org.thymeleaf.context.Context();
            context.setVariable("name", employee.getName());
            context.setVariable("posName", employee.getPosition().getName());
            context.setVariable("joblevelName", employee.getJobLevel().getName());
            context.setVariable("departmentName", employee.getDepartment().getName());
            String mail = templateEngine.process("mail", context);
            helper.setText(mail,true);
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
            logger.error("邮件发送失败" + e.getLocalizedMessage());
        }
    }


}
