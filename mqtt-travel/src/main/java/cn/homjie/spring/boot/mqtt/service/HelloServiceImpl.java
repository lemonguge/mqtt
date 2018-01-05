package cn.homjie.spring.boot.mqtt.service;

import org.springframework.stereotype.Service;

/**
 * @author jiehong.jh
 * @date 2018/1/4
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public void hello(String name) {
        System.out.println("Hello, " + name);
    }
}
