package fooddelivery.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "OrderStatus_table")
@Data
public class OrderStatus {

    @Id
    //@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String orderId;
    private String status;
    private Boolean payYn;
    private String customerOrderStatus;
    private String cookingStatus;
    private String deliveryStatus;
}
