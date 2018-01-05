package cn.homjie.spring.boot.mqtt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.stream.CharacterStreamReadingMessageSource;
import org.springframework.messaging.MessageHandler;

/**
 * @author jiehong.jh
 * @date 2018/1/5
 */
@Configuration
@Profile("action")
public class ActionConfig {

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        System.out.println("----------");
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setServerURIs("tcp://localhost:1883");
        return factory;
    }

    // publisher

    @Bean
    public IntegrationFlow mqttOutFlow() {
        return IntegrationFlows.from(CharacterStreamReadingMessageSource.stdin(),
            e -> e.poller(Pollers.fixedDelay(1000)))
            .transform(p -> p + " sent to MQTT")
            .handle(mqttOutbound())
            .get();
    }

    @Bean
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("mqttPublisher", mqttClientFactory());
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("mqttTopic");
        return messageHandler;
    }

    // consumer

    @Bean
    public IntegrationFlow mqttInFlow() {
        return IntegrationFlows.from(mqttInbound())
            .transform(p -> p + ", received from MQTT")
            .handle(logger())
            .get();
    }

    private LoggingHandler logger() {
        LoggingHandler loggingHandler = new LoggingHandler("INFO");
        loggingHandler.setLoggerName("mqtt");
        return loggingHandler;
    }

    @Bean
    public MessageProducerSupport mqttInbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("mqttConsumer",
            mqttClientFactory(), "mqttTopic");
        adapter.setCompletionTimeout(5000);
        adapter.setQos(1);
        return adapter;
    }
}
