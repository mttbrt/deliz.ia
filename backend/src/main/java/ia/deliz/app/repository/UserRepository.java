package ia.deliz.app.repository;

import ia.deliz.app.model.domain.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(itemResourceRel = "user", collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {
  @Override
  Iterable<UserEntity> findAll(Sort sort);

  @Override
  Page<UserEntity> findAll(Pageable pageable);

  @Override
  <S extends UserEntity> S save(S entity);

  @Override
  <S extends UserEntity> Iterable<S> saveAll(Iterable<S> entities);

  @Override
  Optional<UserEntity> findById(Long aLong);

  @Override
  boolean existsById(Long aLong);

  @Override
  Iterable<UserEntity> findAll();

  @Override
  Iterable<UserEntity> findAllById(Iterable<Long> longs);

  @Override
  long count();

  @Override
  void deleteById(Long aLong);

  @Override
  void delete(UserEntity entity);

  @Override
  void deleteAllById(Iterable<? extends Long> longs);

  @Override
  void deleteAll(Iterable<? extends UserEntity> entities);

  @Override
  void deleteAll();
}
