package ia.deliz.app.repository;

import ia.deliz.app.model.domain.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    itemResourceRel = "table",
    collectionResourceRel = "tables",
    path = "tables")
public interface TableRepository extends JpaRepository<TableEntity, Long> {}
