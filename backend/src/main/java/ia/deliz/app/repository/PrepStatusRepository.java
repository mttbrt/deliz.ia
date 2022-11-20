package ia.deliz.app.repository;

import ia.deliz.app.model.domain.PrepStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    itemResourceRel = "prep-status",
    collectionResourceRel = "prep-statuses",
    path = "prep-statuses")
public interface PrepStatusRepository extends JpaRepository<PrepStatusEntity, Long> {}
