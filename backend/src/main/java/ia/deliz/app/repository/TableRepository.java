package ia.deliz.app.repository;

import ia.deliz.app.model.domain.TableEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(
    itemResourceRel = "table",
    collectionResourceRel = "tables",
    path = "tables")
public interface TableRepository extends PagingAndSortingRepository<TableEntity, Long> {
  @Override
  Iterable<TableEntity> findAll(Sort sort);

  @Override
  Page<TableEntity> findAll(Pageable pageable);

  @Override
  <S extends TableEntity> S save(S entity);

  @Override
  <S extends TableEntity> Iterable<S> saveAll(Iterable<S> entities);

  @Override
  Optional<TableEntity> findById(Long aLong);

  @Override
  boolean existsById(Long aLong);

  @Override
  Iterable<TableEntity> findAll();

  @Override
  Iterable<TableEntity> findAllById(Iterable<Long> longs);

  @Override
  long count();

  @Override
  void deleteById(Long aLong);

  @Override
  void delete(TableEntity entity);

  @Override
  void deleteAllById(Iterable<? extends Long> longs);

  @Override
  void deleteAll(Iterable<? extends TableEntity> entities);

  @Override
  void deleteAll();
}
