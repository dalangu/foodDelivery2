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
        /** Example 1:  new item 
        CustomerOrder customerOrder = new CustomerOrder();
        repository().save(customerOrder);

        Paied paied = new Paied(customerOrder);
        paied.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(ordered.get???()).ifPresent(customerOrder->{
            
            customerOrder // do something
            repository().save(customerOrder);

            Paied paied = new Paied(customerOrder);
            paied.publishAfterCommit();

         });
        */

    }

    public static void cookingStart(OrderAccepted orderAccepted) {
        /** Example 1:  new item 
        CustomerOrder customerOrder = new CustomerOrder();
        repository().save(customerOrder);

        CookingStarted cookingStarted = new CookingStarted(customerOrder);
        cookingStarted.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        repository().findById(orderAccepted.get???()).ifPresent(customerOrder->{
            
            customerOrder // do something
            repository().save(customerOrder);

            CookingStarted cookingStarted = new CookingStarted(customerOrder);
            cookingStarted.publishAfterCommit();

         });
        */

    }
}
