package ia.deliz.app.repository;

import ia.deliz.app.model.domain.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(
    itemResourceRel = "order-menu-item",
    collectionResourceRel = "order-menu-items",
    path = "order-menu-items")
public interface OrderMenuItemRepository extends PagingAndSortingRepository<OrderEntity, Long> {
  @Override
  Iterable<OrderEntity> findAll(Sort sort);

  @Override
  Page<OrderEntity> findAll(Pageable pageable);

  @Override
  <S extends OrderEntity> S save(S entity);

  @Override
  <S extends OrderEntity> Iterable<S> saveAll(Iterable<S> entities);

  @Override
  Optional<OrderEntity> findById(Long aLong);

  @Override
  boolean existsById(Long aLong);

  @Override
  Iterable<OrderEntity> findAll();

  @Override
  Iterable<OrderEntity> findAllById(Iterable<Long> longs);

  @Override
  long count();

  @Override
  void deleteById(Long aLong);

  @Override
  void delete(OrderEntity entity);

  @Override
  void deleteAllById(Iterable<? extends Long> longs);

  @Override
  void deleteAll(Iterable<? extends OrderEntity> entities);

  @Override
  void deleteAll();
}
