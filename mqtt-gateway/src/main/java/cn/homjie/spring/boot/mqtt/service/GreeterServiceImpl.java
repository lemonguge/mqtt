package cn.homjie.spring.boot.mqtt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author jiehong.jh
 * @date 2018/1/4
 */
@Service
public class GreeterServiceImpl implements GreeterService {
    @Autowired
    @Qualifier("helloServiceGateway")
    private HelloService gateway;

    @Override
    public void greet(String name) {
        System.out.println("gateway class: " + gateway.getClass());
        System.out.println("gateway interface:");
        Class<?>[] interfaces = gateway.getClass().getInterfaces();
        for (Class clazz : interfaces) {
            System.out.println("\t" + clazz);
        }
        System.out.println(gateway.hello(name));
    }
}
