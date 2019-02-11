package com.atguigu.amqp;

import com.atguigu.amqp.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02AmqpApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;

    @Test
    public void createExchange(){
//        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
//        System.out.println("创建完成");

//        amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));

        amqpAdmin.declareBinding(new Binding("amqpadmin.queue",Binding.DestinationType.QUEUE,"amqpadmin.exchange","amqp.haha",null));
    }

    @Test
    public void contextLoads() {
        //Message需要自己构建一个；定义消息体内容和消息头
        // rabbitTemplate.send(exchange, routingKey, message);
        //Object 默认当成消息体，只需要传入要发送的对象，自动化序列发送给rabbitmq；
        Map<String,Object> map = new HashMap<>();
        map.put("msg", "这是第一个信息");
        map.put("data", Arrays.asList("helloWorld",123,true));
        //对象被默认序列以后发送出去
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);
    }

    @Test
    public void reciverAndConvert(){

        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);

    }
    @Test
    public void contextLoads2() {
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",new Book("百年孤独", "季羡林"));
    }
    /**
     * 广播
     */
    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("百年孤独", "季羡林"));
    }
}
