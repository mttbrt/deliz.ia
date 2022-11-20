package ia.deliz.app.repository;

import ia.deliz.app.model.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(itemResourceRel = "user", collectionResourceRel = "users", path = "users")
public interface UserRepository extends JpaRepository<UserEntity, Long> {}
