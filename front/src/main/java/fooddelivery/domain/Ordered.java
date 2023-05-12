package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class Ordered extends AbstractEvent {

    private Long id;
    private String orderId;
    private String status;
    private String userId;

    public Ordered(Order aggregate) {
        super(aggregate);
    }

    public Ordered() {
        super();
    }
}
