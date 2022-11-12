package ia.deliz.app.repository;

import ia.deliz.app.model.domain.MenuCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategoryEntity, Long> {}
