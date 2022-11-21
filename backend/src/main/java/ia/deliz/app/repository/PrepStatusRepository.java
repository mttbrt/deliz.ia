package ia.deliz.app.repository;

import ia.deliz.app.model.domain.PrepStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(
    itemResourceRel = "prep-status",
    collectionResourceRel = "prep-statuses",
    path = "prep-statuses")
public interface PrepStatusRepository extends PagingAndSortingRepository<PrepStatusEntity, Long> {
  @Override
  Iterable<PrepStatusEntity> findAll(Sort sort);

  @Override
  Page<PrepStatusEntity> findAll(Pageable pageable);

  @Override
  @RestResource(exported = false)
  <S extends PrepStatusEntity> S save(S entity);

  @Override
  @RestResource(exported = false)
  <S extends PrepStatusEntity> Iterable<S> saveAll(Iterable<S> entities);

  @Override
  Optional<PrepStatusEntity> findById(Long aLong);

  @Override
  boolean existsById(Long aLong);

  @Override
  Iterable<PrepStatusEntity> findAll();

  @Override
  Iterable<PrepStatusEntity> findAllById(Iterable<Long> longs);

  @Override
  long count();

  @Override
  @RestResource(exported = false)
  void deleteById(Long aLong);

  @Override
  @RestResource(exported = false)
  void delete(PrepStatusEntity entity);

  @Override
  @RestResource(exported = false)
  void deleteAllById(Iterable<? extends Long> longs);

  @Override
  @RestResource(exported = false)
  void deleteAll(Iterable<? extends PrepStatusEntity> entities);

  @Override
  @RestResource(exported = false)
  void deleteAll();
}
