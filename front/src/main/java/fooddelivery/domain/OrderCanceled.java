package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderCanceled extends AbstractEvent {

    private Long id;
    private String orderId;
    private String status;
    private String userId;

    public OrderCanceled(Order aggregate) {
        super(aggregate);
    }

    public OrderCanceled() {
        super();
    }
}
