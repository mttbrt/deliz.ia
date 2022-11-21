package ia.deliz.app.repository;

import ia.deliz.app.model.domain.MenuItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(
    itemResourceRel = "menu-item",
    collectionResourceRel = "menu-items",
    path = "menu-items")
public interface MenuItemRepository extends PagingAndSortingRepository<MenuItemEntity, Long> {
  @Override
  Iterable<MenuItemEntity> findAll(Sort sort);

  @Override
  Page<MenuItemEntity> findAll(Pageable pageable);

  @Override
  <S extends MenuItemEntity> S save(S entity);

  @Override
  <S extends MenuItemEntity> Iterable<S> saveAll(Iterable<S> entities);

  @Override
  Optional<MenuItemEntity> findById(Long aLong);

  @Override
  boolean existsById(Long aLong);

  @Override
  Iterable<MenuItemEntity> findAll();

  @Override
  Iterable<MenuItemEntity> findAllById(Iterable<Long> longs);

  @Override
  long count();

  @Override
  void deleteById(Long aLong);

  @Override
  void delete(MenuItemEntity entity);

  @Override
  void deleteAllById(Iterable<? extends Long> longs);

  @Override
  void deleteAll(Iterable<? extends MenuItemEntity> entities);

  @Override
  void deleteAll();
}
