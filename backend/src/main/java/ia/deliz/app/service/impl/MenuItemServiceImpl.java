package ia.deliz.app.service.impl;

import ia.deliz.app.exception.EntityNotFoundException;
import ia.deliz.app.model.domain.MenuCategoryEntity;
import ia.deliz.app.model.domain.MenuItemEntity;
import ia.deliz.app.model.dto.MenuItemDTO;
import ia.deliz.app.repository.MenuCategoryRepository;
import ia.deliz.app.repository.MenuItemRepository;
import ia.deliz.app.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MenuItemServiceImpl implements MenuItemService {

  @Autowired private MenuItemRepository menuItemRepository;
  @Autowired private MenuCategoryRepository menuCategoryRepository;

  @Override
  public Iterable<MenuItemEntity> getMenuItems() {
    return menuItemRepository.findAll();
  }

  @Override
  public MenuItemEntity getMenuItemById(Long id) {
    Optional<MenuItemEntity> existingEntity = menuItemRepository.findById(id);
    if (existingEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No menu item found with id %d", id));
    }

    return existingEntity.get();
  }

  @Override
  public MenuItemEntity createMenuItem(MenuItemDTO dto) {
    Optional<MenuCategoryEntity> categoryEntity =
        menuCategoryRepository.findFirstByName(dto.getCategoryName());
    if (categoryEntity.isEmpty()) {
      throw new EntityNotFoundException(
          String.format("No menu category found with name %s", dto.getCategoryName()));
    }

    MenuItemEntity entity = new MenuItemEntity(dto, categoryEntity.get());
    return menuItemRepository.save(entity);
  }

  @Override
  public MenuItemEntity replaceMenuItem(Long id, MenuItemDTO dto) {
    if (!menuItemRepository.existsById(id)) {
      throw new EntityNotFoundException(String.format("No menu item found with id %d", id));
    }

    Optional<MenuCategoryEntity> categoryEntity =
        menuCategoryRepository.findFirstByName(dto.getCategoryName());
    if (categoryEntity.isEmpty()) {
      throw new EntityNotFoundException(
          String.format("No menu category found with name %s", dto.getCategoryName()));
    }

    MenuItemEntity replacedEntity = new MenuItemEntity(dto, categoryEntity.get());
    return menuItemRepository.save(replacedEntity);
  }

  @Override
  public MenuItemEntity updateMenuItem(Long id, MenuItemDTO dto) {
    Optional<MenuItemEntity> existingEntity = menuItemRepository.findById(id);
    if (existingEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No menu item found with id %d", id));
    }

    Optional<MenuCategoryEntity> categoryEntity =
        menuCategoryRepository.findFirstByName(dto.getCategoryName());
    if (categoryEntity.isEmpty()) {
      throw new EntityNotFoundException(
          String.format("No menu category found with name %s", dto.getCategoryName()));
    }

    MenuItemEntity updatedEntity =
        existingEntity.get().updateNonNullAttributes(dto, categoryEntity.get());
    return menuItemRepository.save(updatedEntity);
  }

  @Override
  public void deleteMenuItem(Long id) {
    if (!menuItemRepository.existsById(id)) {
      throw new EntityNotFoundException(String.format("No menu item found with id %d", id));
    }

    menuItemRepository.deleteById(id);
  }
}
