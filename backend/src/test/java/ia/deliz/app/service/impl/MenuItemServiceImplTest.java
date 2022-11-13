package ia.deliz.app.service.impl;

import com.google.common.collect.Iterables;
import ia.deliz.app.exception.EntityNotFoundException;
import ia.deliz.app.model.domain.MenuCategoryEntity;
import ia.deliz.app.model.domain.MenuItemEntity;
import ia.deliz.app.model.dto.MenuItemDTO;
import ia.deliz.app.repository.MenuCategoryRepository;
import ia.deliz.app.repository.MenuItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MenuItemServiceImplTest {

  @InjectMocks private MenuItemServiceImpl service;

  @Mock private MenuItemRepository menuItemRepository;
  @Mock private MenuCategoryRepository menuCategoryRepository;

  @Test
  void get_all_menu_items_in_db() {
    MenuCategoryEntity category = new MenuCategoryEntity(1L, "category-01");
    List<MenuItemEntity> list = new LinkedList<>();
    list.add(new MenuItemEntity(1L, "item-01", null, 1.5F, category));
    list.add(new MenuItemEntity(2L, "item-02", null, 17.99F, category));
    when(menuItemRepository.findAll()).thenReturn(list);

    // Test
    Iterable<MenuItemEntity> out = service.getMenuItems();

    // Verify output
    assertEquals(2, Iterables.size(out));
    verify(menuItemRepository, times(1)).findAll();
  }

  @Test
  void get_menu_item_by_id() {
    MenuCategoryEntity category = new MenuCategoryEntity(1L, "category-01");
    MenuItemEntity in = new MenuItemEntity(1L, "item-01", null, 1.5F, category);
    when(menuItemRepository.findById(1L)).thenReturn(Optional.of(in));

    // Test
    MenuItemEntity out = service.getMenuItemById(1L);

    // Verify output
    assertEquals(in, out);
    verify(menuItemRepository, times(1)).findById(any());
  }

  @Test
  void exception_is_thrown_when_getting_invalid_id() {
    when(menuItemRepository.findById(1L)).thenReturn(Optional.empty());

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.getMenuItemById(1L));

    // Verify output
    assertEquals("No menu item found with id 1", exception.getMessage());
    verify(menuItemRepository, times(1)).findById(any());
  }

  @Test
  void add_new_menu_item() {
    MenuItemDTO in = new MenuItemDTO("item-01", null, 1.5F, "Drinks");
    MenuCategoryEntity category = new MenuCategoryEntity(1L, "Drinks");
    MenuItemEntity entity = new MenuItemEntity(in, category);
    when(menuCategoryRepository.findFirstByName("Drinks")).thenReturn(Optional.of(category));
    when(menuItemRepository.save(entity)).thenReturn(entity);

    // Test
    MenuItemEntity out = service.createMenuItem(in);

    // Verify output
    assertEquals(in.getName(), out.getName());
    assertEquals(in.getDescription(), out.getDescription());
    assertEquals(in.getPrice(), out.getPrice());
    assertEquals(in.getCategoryName(), out.getCategory().getName());
    verify(menuItemRepository, times(1)).save(any());
  }

  @Test
  void exception_is_thrown_when_adding_new_item_with_wrong_category_id() {
    MenuItemDTO in = new MenuItemDTO("item-01", null, 1.5F, "Drinks");
    when(menuCategoryRepository.findFirstByName("Drinks")).thenReturn(Optional.empty());

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.createMenuItem(in));

    // Verify output
    assertEquals("No menu category found with name Drinks", exception.getMessage());
    verify(menuCategoryRepository, times(1)).findFirstByName(any());
    verify(menuItemRepository, times(0)).save(any());
  }

  @Test
  void replace_menu_item_in_db() {
    MenuItemDTO in = new MenuItemDTO("item-01", null, 1.5F, "Drinks");
    MenuCategoryEntity category = new MenuCategoryEntity(1L, "Drinks");
    MenuItemEntity entity = new MenuItemEntity(in, category);
    when(menuItemRepository.existsById(1L)).thenReturn(true);
    when(menuCategoryRepository.findFirstByName("Drinks")).thenReturn(Optional.of(category));
    when(menuItemRepository.save(entity)).thenReturn(entity);

    // Test
    MenuItemEntity out = service.replaceMenuItem(1L, in);

    // Verify output
    assertEquals(in.getName(), out.getName());
    assertEquals(in.getDescription(), out.getDescription());
    assertEquals(in.getPrice(), out.getPrice());
    assertEquals(in.getCategoryName(), out.getCategory().getName());
    verify(menuItemRepository, times(1)).existsById(any());
    verify(menuItemRepository, times(1)).save(any());
  }

  @Test
  void exception_is_thrown_when_replacing_wrong_id() {
    MenuItemDTO in = new MenuItemDTO("item-01", null, 1.5F, "Drinks");
    when(menuItemRepository.existsById(1L)).thenReturn(false);

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.replaceMenuItem(1L, in));

    // Verify output
    assertEquals("No menu item found with id 1", exception.getMessage());
    verify(menuItemRepository, times(1)).existsById(any());
    verify(menuItemRepository, times(0)).save(any());
  }

  @Test
  void exception_is_thrown_when_replacing_new_item_with_wrong_category_id() {
    MenuItemDTO in = new MenuItemDTO("item-01", null, 1.5F, "Drinks");
    when(menuItemRepository.existsById(1L)).thenReturn(true);
    when(menuCategoryRepository.findFirstByName("Drinks")).thenReturn(Optional.empty());

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.replaceMenuItem(1L, in));

    // Verify output
    assertEquals("No menu category found with name Drinks", exception.getMessage());
    verify(menuItemRepository, times(1)).existsById(any());
    verify(menuCategoryRepository, times(1)).findFirstByName(any());
    verify(menuItemRepository, times(0)).save(any());
  }

  @Test
  void update_menu_item_in_db() {
    MenuCategoryEntity category = new MenuCategoryEntity(1L, "Drinks");
    MenuItemDTO in = new MenuItemDTO("item-02", "description", 0.5F, "Drinks");
    MenuItemEntity existingEntity = new MenuItemEntity(1L, "item-01", null, 1.5F, category);
    when(menuItemRepository.findById(1L)).thenReturn(Optional.of(existingEntity));
    when(menuCategoryRepository.findFirstByName("Drinks")).thenReturn(Optional.of(category));
    when(menuItemRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

    // Test
    MenuItemEntity out = service.updateMenuItem(1L, in);

    // Verify output
    assertEquals(in.getName(), out.getName());
    assertEquals(in.getDescription(), out.getDescription());
    assertEquals(in.getPrice(), out.getPrice());
    assertEquals(in.getCategoryName(), out.getCategory().getName());
    verify(menuItemRepository, times(1)).findById(any());
    verify(menuItemRepository, times(1)).save(any());
  }

  @Test
  void exception_is_thrown_when_updating_wrong_id() {
    MenuItemDTO in = new MenuItemDTO("item-01", null, 1.5F, "Drinks");
    when(menuItemRepository.findById(1L)).thenReturn(Optional.empty());

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.updateMenuItem(1L, in));

    // Verify output
    assertEquals("No menu item found with id 1", exception.getMessage());
    verify(menuItemRepository, times(1)).findById(any());
    verify(menuItemRepository, times(0)).save(any());
  }

  @Test
  void exception_is_thrown_when_updating_new_item_with_wrong_category_id() {
    MenuCategoryEntity category = new MenuCategoryEntity(1L, "category-01");
    MenuItemDTO in = new MenuItemDTO("item-02", "description", 0.5F, "Drinks");
    MenuItemEntity existingEntity = new MenuItemEntity(1L, "item-01", null, 1.5F, category);
    when(menuItemRepository.findById(1L)).thenReturn(Optional.of(existingEntity));
    when(menuCategoryRepository.findFirstByName("Drinks")).thenReturn(Optional.empty());

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.updateMenuItem(1L, in));

    // Verify output
    assertEquals("No menu category found with name Drinks", exception.getMessage());
    verify(menuItemRepository, times(1)).findById(any());
    verify(menuCategoryRepository, times(1)).findFirstByName(any());
    verify(menuItemRepository, times(0)).save(any());
  }

  @Test
  void delete_menu_item_by_id() {
    when(menuItemRepository.existsById(1L)).thenReturn(true);

    // Test
    service.deleteMenuItem(1L);

    // Verify output
    verify(menuItemRepository, times(1)).existsById(any());
    verify(menuItemRepository, times(1)).deleteById(any());
  }

  @Test
  void exception_is_thrown_when_deleting_invalid_id() {
    when(menuItemRepository.existsById(1L)).thenReturn(false);

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.deleteMenuItem(1L));

    // Verify output
    assertEquals("No menu item found with id 1", exception.getMessage());
    verify(menuItemRepository, times(1)).existsById(any());
    verify(menuItemRepository, times(0)).deleteById(any());
  }
}
