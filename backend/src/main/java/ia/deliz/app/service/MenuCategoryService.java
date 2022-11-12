package ia.deliz.app.service;

import ia.deliz.app.model.domain.MenuCategoryEntity;
import ia.deliz.app.model.dto.MenuCategoryDTO;

public interface MenuCategoryService {

  Iterable<MenuCategoryEntity> getMenuCategories();

  MenuCategoryEntity getMenuCategoryById(Long id);

  MenuCategoryEntity createMenuCategory(MenuCategoryDTO id);

  MenuCategoryEntity replaceMenuCategory(Long id, MenuCategoryDTO dto);

  MenuCategoryEntity updateMenuCategory(Long id, MenuCategoryDTO dto);

  void deleteMenuCategory(Long id);
}
