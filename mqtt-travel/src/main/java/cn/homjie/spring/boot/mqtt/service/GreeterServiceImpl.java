package cn.homjie.spring.boot.mqtt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

/**
 * @author jiehong.jh
 * @date 2018/1/4
 */
@Service
public class GreeterServiceImpl implements GreeterService {
    @Autowired
    @Qualifier("helloServiceChannel")
    private MessageChannel channel;

    @Override
    public void greet(String name) {
        System.out.println("channel class: " + channel.getClass());
        System.out.println("channel interface:");
        Class<?>[] interfaces = channel.getClass().getInterfaces();
        for (Class clazz : interfaces) {
            System.out.println("\t" + clazz);
        }
        channel.send(MessageBuilder.withPayload(name).build());
    }
}
