package ia.deliz.app.service.impl;

import ia.deliz.app.exception.EntityNotFoundException;
import ia.deliz.app.model.domain.MenuCategoryEntity;
import ia.deliz.app.model.dto.MenuCategoryDTO;
import ia.deliz.app.repository.MenuCategoryRepository;
import ia.deliz.app.service.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuCategoryServiceImpl implements MenuCategoryService {

  @Autowired private MenuCategoryRepository menuCategoryRepository;

  @Override
  public Iterable<MenuCategoryEntity> getMenuCategories() {
    return menuCategoryRepository.findAll();
  }

  @Override
  public MenuCategoryEntity getMenuCategoryById(Long id) {
    Optional<MenuCategoryEntity> existingEntity = menuCategoryRepository.findById(id);
    if (existingEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No menu category found with id %d", id));
    }

    return existingEntity.get();
  }

  @Override
  public MenuCategoryEntity createMenuCategory(MenuCategoryDTO dto) {
    MenuCategoryEntity entity = new MenuCategoryEntity(dto);
    return menuCategoryRepository.save(entity);
  }

  @Override
  public MenuCategoryEntity replaceMenuCategory(Long id, MenuCategoryDTO dto) {
    if (!menuCategoryRepository.existsById(id)) {
      throw new EntityNotFoundException(String.format("No menu category found with id %d", id));
    }

    MenuCategoryEntity replacedEntity = new MenuCategoryEntity(dto);
    return menuCategoryRepository.save(replacedEntity);
  }

  @Override
  public MenuCategoryEntity updateMenuCategory(Long id, MenuCategoryDTO dto) {
    Optional<MenuCategoryEntity> existingEntity = menuCategoryRepository.findById(id);
    if (existingEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No menu category found with id %d", id));
    }

    MenuCategoryEntity updatedEntity = existingEntity.get().updateNonNullAttributes(dto);
    return menuCategoryRepository.save(updatedEntity);
  }

  @Override
  public void deleteMenuCategory(Long id) {
    if (!menuCategoryRepository.existsById(id)) {
      throw new EntityNotFoundException(String.format("No menu category found with id %d", id));
    }

    menuCategoryRepository.deleteById(id);
  }
}
