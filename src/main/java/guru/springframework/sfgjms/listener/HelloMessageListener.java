package guru.springframework.sfgjms.listener;

import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

/**
 * Created by Jiang Wensi on 7/11/2020
 */
@Component
public class HelloMessageListener {
    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(
            @Headers MessageHeaders headers,
            @Payload HelloWorldMessage helloWorldMessage,
            Message message
        ){
        System.out.println("I Got a Message!!!!");
        System.out.println(helloWorldMessage);
//        System.out.println(headers.get("JMSXDeliveryCount"));
//        System.out.println();
//        throw new RuntimeException("foo");
    }
}
