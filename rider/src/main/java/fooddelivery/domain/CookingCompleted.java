package fooddelivery.domain;

import fooddelivery.domain.*;
import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class CookingCompleted extends AbstractEvent {

    private Long id;
    private String orderId;
    private String status;
}
