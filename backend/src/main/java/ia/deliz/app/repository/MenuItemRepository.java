package ia.deliz.app.repository;

import ia.deliz.app.model.domain.MenuItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    itemResourceRel = "menu-item",
    collectionResourceRel = "menu-items",
    path = "menu-items")
public interface MenuItemRepository extends JpaRepository<MenuItemEntity, Long> {}
