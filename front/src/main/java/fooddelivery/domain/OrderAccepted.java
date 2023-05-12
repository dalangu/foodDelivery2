package fooddelivery.domain;

import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.Data;

@Data
public class OrderAccepted extends AbstractEvent {

    private Long id;
    private String orderId;
    private String status;
}
