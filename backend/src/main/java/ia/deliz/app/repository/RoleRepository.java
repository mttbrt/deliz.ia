package ia.deliz.app.repository;

import ia.deliz.app.model.domain.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(itemResourceRel = "role", collectionResourceRel = "roles", path = "roles")
public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long> {
  @Override
  Iterable<RoleEntity> findAll(Sort sort);

  @Override
  Page<RoleEntity> findAll(Pageable pageable);

  @Override
  @RestResource(exported = false)
  <S extends RoleEntity> S save(S entity);

  @Override
  @RestResource(exported = false)
  <S extends RoleEntity> Iterable<S> saveAll(Iterable<S> entities);

  @Override
  Optional<RoleEntity> findById(Long aLong);

  @Override
  boolean existsById(Long aLong);

  @Override
  Iterable<RoleEntity> findAll();

  @Override
  Iterable<RoleEntity> findAllById(Iterable<Long> longs);

  @Override
  long count();

  @Override
  @RestResource(exported = false)
  void deleteById(Long aLong);

  @Override
  @RestResource(exported = false)
  void delete(RoleEntity entity);

  @Override
  @RestResource(exported = false)
  void deleteAllById(Iterable<? extends Long> longs);

  @Override
  @RestResource(exported = false)
  void deleteAll(Iterable<? extends RoleEntity> entities);

  @Override
  @RestResource(exported = false)
  void deleteAll();
}
