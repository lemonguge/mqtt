package cn.homjie.spring.boot.mqtt;

import cn.homjie.spring.boot.mqtt.service.GreeterService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:integration.xml")
public class TravelApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(TravelApplication.class, args);
        GreeterService greeterService = ctx.getBean("greeterServiceImpl", GreeterService.class);
        // 1、GreeterService 将消息发送给 MessageChannel
        // 2、service-activator 负责将任何发送到 helloWorldChannel 的消息调度到 HelloService#hello()
        greeterService.greet("Spring Integration!");
    }
}
