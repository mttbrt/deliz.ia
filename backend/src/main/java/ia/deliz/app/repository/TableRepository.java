package ia.deliz.app.repository;

import ia.deliz.app.model.domain.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {}
