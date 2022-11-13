package ia.deliz.app.service;

import ia.deliz.app.model.domain.MenuItemEntity;
import ia.deliz.app.model.dto.MenuItemDTO;

public interface MenuItemService {

  Iterable<MenuItemEntity> getMenuItems();

  MenuItemEntity getMenuItemById(Long id);

  MenuItemEntity createMenuItem(MenuItemDTO dto);

  MenuItemEntity replaceMenuItem(Long id, MenuItemDTO dto);

  MenuItemEntity updateMenuItem(Long id, MenuItemDTO dto);

  void deleteMenuItem(Long id);
}
