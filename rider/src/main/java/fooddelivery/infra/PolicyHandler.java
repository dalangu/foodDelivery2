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
    DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CookingCompleted'"
    )
    public void wheneverCookingCompleted_RidersPickDelivery(
        @Payload CookingCompleted cookingCompleted
    ) {
        CookingCompleted event = cookingCompleted;
        System.out.println(
            "\n\n##### listener RidersPickDelivery : " +
            cookingCompleted +
            "\n\n"
        );

        // Sample Logic //
        Delivery.ridersPickDelivery(event);
    }
}
