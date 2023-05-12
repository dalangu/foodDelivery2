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
        /** Example 1:  new item 
        Delivery delivery = new Delivery();
        repository().save(delivery);

        DeliveryPicked deliveryPicked = new DeliveryPicked(delivery);
        deliveryPicked.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(cookingCompleted.get???()).ifPresent(delivery->{
            
            delivery // do something
            repository().save(delivery);

            DeliveryPicked deliveryPicked = new DeliveryPicked(delivery);
            deliveryPicked.publishAfterCommit();

         });
        */

    }
}
