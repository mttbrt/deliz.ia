package ia.deliz.app.repository;

import ia.deliz.app.model.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    itemResourceRel = "order-menu-item",
    collectionResourceRel = "order-menu-items",
    path = "order-menu-items")
public interface OrderMenuItemRepository extends JpaRepository<OrderEntity, Long> {}
