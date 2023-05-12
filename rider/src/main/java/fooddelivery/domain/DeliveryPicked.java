package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class DeliveryPicked extends AbstractEvent {

    private Long id;
    private String orderId;
    private String status;

    public DeliveryPicked(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryPicked() {
        super();
    }
}
