package fooddelivery.domain;

import fooddelivery.StoreApplication;
import fooddelivery.domain.CookingCompleted;
import fooddelivery.domain.CookingStarted;
import fooddelivery.domain.OrderAccepted;
import fooddelivery.domain.OrderDenied;
import fooddelivery.domain.Paied;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CustomerOrder_table")
@Data
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;

    private String status;

    @PostPersist
    public void onPostPersist() {
        Paied paied = new Paied(this);
        paied.publishAfterCommit();

        OrderAccepted orderAccepted = new OrderAccepted(this);
        orderAccepted.publishAfterCommit();

        OrderDenied orderDenied = new OrderDenied(this);
        orderDenied.publishAfterCommit();

        CookingStarted cookingStarted = new CookingStarted(this);
        cookingStarted.publishAfterCommit();

        CookingCompleted cookingCompleted = new CookingCompleted(this);
        cookingCompleted.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static CustomerOrderRepository repository() {
        CustomerOrderRepository customerOrderRepository = StoreApplication.applicationContext.getBean(
            CustomerOrderRepository.class
        );
        return customerOrderRepository;
    }

    public static void pay(Ordered ordered) {
      
        repository().findByOrderId(ordered.getOrderId()).ifPresent(customerOrder->{
            
            customerOrder.setOrderId(ordered.getOrderId());
            repository().save(customerOrder);
         });
        

    }

    public static void cookingStart(OrderAccepted orderAccepted) {
      
        repository().findByOrderId(orderAccepted.getOrderId()).ifPresent(customerOrder->{
            
            customerOrder.setStatus(orderAccepted.getStatus());
            repository().save(customerOrder);
            
         });
        

    }
}
