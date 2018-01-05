package cn.homjie.spring.boot.mqtt;

import cn.homjie.spring.boot.mqtt.OutboundConfig.MyGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author jiehong.jh
 * @date 2018/1/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("bound")
public class BoundConfigTest {

    @Autowired
    private MyGateway gateway;

    @Test
    public void test() {
        gateway.sendToMqtt("foo");
    }

}