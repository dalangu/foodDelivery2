package fooddelivery.infra;

import fooddelivery.domain.*;
import fooddelivery.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderStatusViewHandler {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrdered_then_CREATE_1 (@Payload Ordered ordered) {
        try {

            if (!ordered.validate()) return;

            // view 객체 생성
            OrderStatus orderStatus = new OrderStatus();
            // view 객체에 이벤트의 Value 를 set 함
            orderStatus.setOrderId(ordered.getOrderId());
            orderStatus.setStatus(ordered.getStatus());
            orderStatus.setCustomerOrderStatus(-);
            orderStatus.setCookingStatus(-);
            orderStatus.setDeliveryStatus(-);
            orderStatus.setPayYn(false);
            // view 레파지 토리에 save
            orderStatusRepository.save(orderStatus);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaied_then_UPDATE_1(@Payload Paied paied) {
        try {
            if (!paied.validate()) return;
                // view 객체 조회

                List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(paied.getOrderId());
                for(OrderStatus orderStatus : orderStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderStatus.setPayYn(true);
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderAccepted_then_UPDATE_2(@Payload OrderAccepted orderAccepted) {
        try {
            if (!orderAccepted.validate()) return;
                // view 객체 조회

                List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(orderAccepted.getOrderId());
                for(OrderStatus orderStatus : orderStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderStatus.setCustomerOrderStatus(orderAccepted.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderDenied_then_UPDATE_3(@Payload OrderDenied orderDenied) {
        try {
            if (!orderDenied.validate()) return;
                // view 객체 조회

                List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(orderDenied.getOrderId());
                for(OrderStatus orderStatus : orderStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderStatus.setCustomerOrderStatus(orderDenied.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookingStarted_then_UPDATE_4(@Payload CookingStarted cookingStarted) {
        try {
            if (!cookingStarted.validate()) return;
                // view 객체 조회

                List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(cookingStarted.getOrderId());
                for(OrderStatus orderStatus : orderStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderStatus.setCookingStatus(cookingStarted.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenCookingCompleted_then_UPDATE_5(@Payload CookingCompleted cookingCompleted) {
        try {
            if (!cookingCompleted.validate()) return;
                // view 객체 조회

                List<OrderStatus> orderStatusList = orderStatusRepository.findByOrderId(cookingCompleted.getOrderId());
                for(OrderStatus orderStatus : orderStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderStatus.setCookingStatus(cookingCompleted.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryPicked_then_UPDATE_6(@Payload DeliveryPicked deliveryPicked) {
        try {
            if (!deliveryPicked.validate()) return;
                // view 객체 조회

                List<OrderStatus> orderStatusList = orderStatusRepository.findByDeliveryStatus(deliveryPicked.getStatus());
                for(OrderStatus orderStatus : orderStatusList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    orderStatus.setDeliveryStatus(deliveryPicked.getStatus());
                // view 레파지 토리에 save
                orderStatusRepository.save(orderStatus);
                }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

