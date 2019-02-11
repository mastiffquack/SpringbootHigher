package com.atguigu.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot04TaskApplicationTests {

    @Autowired
    JavaMailSenderImpl mailSender;

    @Test
    public void contextLoads() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("springboot测试邮件1");
        message.setText("springboot测试Text");

        message.setTo("ouhayo@qq.com");
        message.setFrom("shenweiuser@163.com");

        mailSender.send(message);
    }

    @Test
    public void test02() throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);;

        helper.setSubject("springboot测试邮件1");
        helper.setText("<b style='color:red;'>springboot测试Text</b>",true);

        helper.setTo("ouhayo@qq.com");
        helper.setFrom("shenweiuser@163.com");

        helper.addAttachment("1.jpg",new File("D:\\用户目录\\我的图片\\45b54f7effac5ad23ea7cdb541276ee5.jpg"));
        helper.addAttachment("2.jpg",new File("D:\\用户目录\\我的图片\\01625b164d855d6c86eb4d8becbb8481.jpg"));

        mailSender.send(mimeMessage);
    }
}
