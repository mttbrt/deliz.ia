package ia.deliz.app.repository;

import ia.deliz.app.model.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    itemResourceRel = "order",
    collectionResourceRel = "orders",
    path = "orders")
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {}
