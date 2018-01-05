package cn.homjie.spring.boot.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ActionApplication {

    /**
     * Load the Spring Integration Application Context
     *
     * @param args - command line arguments
     */
    public static void main(final String... args) {

        log.info("\n========================================================="
            + "\n                                                         "
            + "\n          Welcome to Spring Integration!                 "
            + "\n                                                         "
            + "\n    For more information please visit:                   "
            + "\n    https://spring.io/projects/spring-integration        "
            + "\n                                                         "
            + "\n=========================================================");

        log.info("\n========================================================="
            + "\n                                                          "
            + "\n    This is the MQTT Sample -                             "
            + "\n                                                          "
            + "\n    Please enter some text and press return. The entered  "
            + "\n    Message will be sent to the configured MQTT topic,    "
            + "\n    then again immediately retrieved from the Message     "
            + "\n    Broker and ultimately printed to the command line.    "
            + "\n                                                          "
            + "\n=========================================================");

        SpringApplication application = new SpringApplication(ActionApplication.class);
        application.setAdditionalProfiles("action");
        application.run(args);
    }
}
