package fooddelivery.infra;

import fooddelivery.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomerOrderHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<CustomerOrder>> {

    @Override
    public EntityModel<CustomerOrder> process(
        EntityModel<CustomerOrder> model
    ) {
        return model;
    }
}
