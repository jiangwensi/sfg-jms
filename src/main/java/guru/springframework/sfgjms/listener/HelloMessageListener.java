package guru.springframework.sfgjms.listener;

import guru.springframework.sfgjms.config.JmsConfig;
import guru.springframework.sfgjms.model.HelloWorldMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.UUID;

/**
 * Created by Jiang Wensi on 7/11/2020
 */
@RequiredArgsConstructor
@Component
public class HelloMessageListener {
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.MY_QUEUE)
    public void listen(
            @Headers MessageHeaders headers,
            @Payload HelloWorldMessage helloWorldMessage,
            Message message
    ) {
//        System.out.println("I Got a Message!!!!");
//        System.out.println(helloWorldMessage);
//        System.out.println(headers.get("JMSXDeliveryCount"));
//        System.out.println();
//        throw new RuntimeException("foo");
    }

    @JmsListener(destination = JmsConfig.MY_SEND_REC_QUEUE)
    public void listenForHello(@Payload HelloWorldMessage helloWorldMessage, @Headers MessageHeaders headers,
                               Message message) throws JMSException {
        HelloWorldMessage payloadMsg = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("World")
                .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMsg);
    }
}
