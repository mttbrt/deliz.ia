package ia.deliz.app.repository;

import ia.deliz.app.model.domain.PrepStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrepStatusRepository extends JpaRepository<PrepStatusEntity, Long> {
  Optional<PrepStatusEntity> findFirstByName(String name);
}
