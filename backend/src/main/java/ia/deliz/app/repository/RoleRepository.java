package ia.deliz.app.repository;

import ia.deliz.app.model.domain.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(itemResourceRel = "role", collectionResourceRel = "roles", path = "roles")
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {}
