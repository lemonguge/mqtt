package cn.homjie.spring.boot.mqtt;

import cn.homjie.spring.boot.mqtt.service.GreeterService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:integration.xml")
public class GatewayApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(GatewayApplication.class, args);
        GreeterService greeterService = ctx.getBean("greeterServiceImpl", GreeterService.class);
        // 1、网关实现了 HelloService 接口中的方法，该方法通过 helloWorldReplyChannel 通道对请求进行调度
        // 2、通过配置服务激活器(service-activator)，任何发给 helloWorldReplyChannel 的消息都会传给 HelloService#hello()
        // 3、服务激活器(service-activator)通过下面任意一种方式处理响应：
        // 一种是定义在服务激活器中的输出通道(output-channel)，网关自动创建一个临时的匿名、点对点回复通道；
        // 另一种是定义在消息头中的回复通道，监听此通道，并将其添加到消息的replyHeader中。
        // 4、通道接受这个响应，将其作为消息，通过回复通道，将其转换成服务中定义的返回值；
        // 5、最后网关返回响应给调用者，如本例的 greeterService
        greeterService.greet("Spring Integration!");
    }
}
