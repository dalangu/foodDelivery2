package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OrderAccepted extends AbstractEvent {

    private Long id;
    private String orderId;
    private String status;

    public OrderAccepted(CustomerOrder aggregate) {
        super(aggregate);
    }

    public OrderAccepted() {
        super();
    }
}
