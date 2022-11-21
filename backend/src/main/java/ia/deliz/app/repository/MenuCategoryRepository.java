package ia.deliz.app.repository;

import ia.deliz.app.model.domain.MenuCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(
    itemResourceRel = "menu-category",
    collectionResourceRel = "menu-categories",
    path = "menu-categories")
public interface MenuCategoryRepository
    extends PagingAndSortingRepository<MenuCategoryEntity, Long> {
  @Override
  Iterable<MenuCategoryEntity> findAll(Sort sort);

  @Override
  Page<MenuCategoryEntity> findAll(Pageable pageable);

  @Override
  <S extends MenuCategoryEntity> S save(S entity);

  @Override
  <S extends MenuCategoryEntity> Iterable<S> saveAll(Iterable<S> entities);

  @Override
  Optional<MenuCategoryEntity> findById(Long aLong);

  @Override
  boolean existsById(Long aLong);

  @Override
  Iterable<MenuCategoryEntity> findAll();

  @Override
  Iterable<MenuCategoryEntity> findAllById(Iterable<Long> longs);

  @Override
  long count();

  @Override
  void deleteById(Long aLong);

  @Override
  void delete(MenuCategoryEntity entity);

  @Override
  void deleteAllById(Iterable<? extends Long> longs);

  @Override
  void deleteAll(Iterable<? extends MenuCategoryEntity> entities);

  @Override
  void deleteAll();
}
