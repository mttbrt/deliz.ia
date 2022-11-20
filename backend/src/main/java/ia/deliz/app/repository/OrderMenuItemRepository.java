package ia.deliz.app.repository;

import ia.deliz.app.model.domain.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMenuItemRepository extends JpaRepository<OrderEntity, Long> {}
