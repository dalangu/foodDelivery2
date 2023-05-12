package fooddelivery.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fooddelivery.config.kafka.KafkaProcessor;
import fooddelivery.domain.*;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    CustomerOrderRepository customerOrderRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Ordered'"
    )
    public void wheneverOrdered_Pay(@Payload Ordered ordered) {
        Ordered event = ordered;
        System.out.println("\n\n##### listener Pay : " + ordered + "\n\n");

        // Sample Logic //
        CustomerOrder.pay(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderAccepted'"
    )
    public void wheneverOrderAccepted_CookingStart(
        @Payload OrderAccepted orderAccepted
    ) {
        OrderAccepted event = orderAccepted;
        System.out.println(
            "\n\n##### listener CookingStart : " + orderAccepted + "\n\n"
        );

        // Sample Logic //
        CustomerOrder.cookingStart(event);
    }
}
