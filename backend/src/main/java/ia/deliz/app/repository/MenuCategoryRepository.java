package ia.deliz.app.repository;

import ia.deliz.app.model.domain.MenuCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    itemResourceRel = "menu-category",
    collectionResourceRel = "menu-categories",
    path = "menu-categories")
public interface MenuCategoryRepository extends JpaRepository<MenuCategoryEntity, Long> {}
