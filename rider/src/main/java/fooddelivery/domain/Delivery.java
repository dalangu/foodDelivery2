package fooddelivery.domain;

import fooddelivery.RiderApplication;
import fooddelivery.domain.DeliveryCompleted;
import fooddelivery.domain.DeliveryPicked;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Delivery_table")
@Data
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;

    private String status;

    @PostPersist
    public void onPostPersist() {
        DeliveryPicked deliveryPicked = new DeliveryPicked(this);
        deliveryPicked.publishAfterCommit();

        DeliveryCompleted deliveryCompleted = new DeliveryCompleted(this);
        deliveryCompleted.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static DeliveryRepository repository() {
        DeliveryRepository deliveryRepository = RiderApplication.applicationContext.getBean(
            DeliveryRepository.class
        );
        return deliveryRepository;
    }

    public static void ridersPickDelivery(CookingCompleted cookingCompleted) {
       
        repository().findByOrderId(cookingCompleted.getOrderId()).ifPresent(delivery->{
            
            delivery.setOrderId(cookingCompleted.getOrderId()); // do something
            repository().save(delivery);

         });
       

    }
}
