package fooddelivery.domain;

import fooddelivery.FrontApplication;
import fooddelivery.domain.OrderCanceled;
import fooddelivery.domain.Ordered;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Order_table")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;

    private String status;

    private String userId;

    @PostPersist
    public void onPostPersist() {
        Ordered ordered = new Ordered(this);
        ordered.publishAfterCommit();
    }

    @PreRemove
    public void onPreRemove() {
        OrderCanceled orderCanceled = new OrderCanceled(this);
        orderCanceled.publishAfterCommit();
    }

    public static OrderRepository repository() {
        OrderRepository orderRepository = FrontApplication.applicationContext.getBean(
            OrderRepository.class
        );
        return orderRepository;
    }
}
