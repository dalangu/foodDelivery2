package fooddelivery.domain;

import fooddelivery.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "customerOrders",
    path = "customerOrders"
)
public interface CustomerOrderRepository
    extends PagingAndSortingRepository<CustomerOrder, Long> {
    java.util.Optional<Delivery> findByOrderId(Long id);
}
