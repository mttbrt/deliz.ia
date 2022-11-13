package ia.deliz.app.repository;

import ia.deliz.app.model.domain.MenuCategoryEntity;
import ia.deliz.app.model.domain.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategoryEntity, Long> {
  Optional<MenuCategoryEntity> findFirstByName(String name);
}
