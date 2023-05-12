package fooddelivery.domain;

import fooddelivery.infra.AbstractEvent;
import java.util.*;
import lombok.Data;

@Data
public class Paied extends AbstractEvent {

    private Long id;
    private String orderId;
    private Boolean payYn;
    private String status;
}
