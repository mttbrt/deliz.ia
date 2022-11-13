package ia.deliz.app.service.impl;

import com.google.common.collect.Iterables;
import ia.deliz.app.exception.EntityNotFoundException;
import ia.deliz.app.model.domain.MenuCategoryEntity;
import ia.deliz.app.model.dto.MenuCategoryDTO;
import ia.deliz.app.repository.MenuCategoryRepository;
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
class MenuCategoryServiceImplTest {

  @InjectMocks private MenuCategoryServiceImpl service;

  @Mock private MenuCategoryRepository repository;

  @Test
  void get_all_menu_categories_in_db() {
    List<MenuCategoryEntity> list = new LinkedList<>();
    list.add(new MenuCategoryEntity(1L, "category-01"));
    list.add(new MenuCategoryEntity(2L, "category-02"));
    when(repository.findAll()).thenReturn(list);

    // Test
    Iterable<MenuCategoryEntity> out = service.getMenuCategories();

    // Verify output
    assertEquals(2, Iterables.size(out));
    verify(repository, times(1)).findAll();
  }

  @Test
  void get_menu_category_by_id() {
    MenuCategoryEntity in = new MenuCategoryEntity(1L, "category-01");
    when(repository.findById(1L)).thenReturn(Optional.of(in));

    // Test
    MenuCategoryEntity out = service.getMenuCategoryById(1L);

    // Verify output
    assertEquals(in, out);
    verify(repository, times(1)).findById(any());
  }

  @Test
  void exception_is_thrown_when_getting_invalid_id() {
    when(repository.findById(1L)).thenReturn(Optional.empty());

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.getMenuCategoryById(1L));

    // Verify output
    assertEquals("No menu category found with id 1", exception.getMessage());
    verify(repository, times(1)).findById(any());
  }

  @Test
  void add_new_menu_category() {
    MenuCategoryDTO in = new MenuCategoryDTO("category-01");
    MenuCategoryEntity entity = new MenuCategoryEntity(in);
    when(repository.save(entity)).thenReturn(entity);

    // Test
    MenuCategoryEntity out = service.createMenuCategory(in);

    // Verify output
    assertEquals(in.getName(), out.getName());
    verify(repository, times(1)).save(any());
  }

  @Test
  void replace_menu_category_in_db() {
    MenuCategoryDTO in = new MenuCategoryDTO("category-01");
    MenuCategoryEntity entity = new MenuCategoryEntity(in);
    when(repository.existsById(1L)).thenReturn(true);
    when(repository.save(entity)).thenReturn(entity);

    // Test
    MenuCategoryEntity out = service.replaceMenuCategory(1L, in);

    // Verify output
    assertEquals(in.getName(), out.getName());
    verify(repository, times(1)).existsById(any());
    verify(repository, times(1)).save(any());
  }

  @Test
  void exception_is_thrown_when_replacing_wrong_id() {
    MenuCategoryDTO in = new MenuCategoryDTO("category-01");
    when(repository.existsById(1L)).thenReturn(false);

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.replaceMenuCategory(1L, in));

    // Verify output
    assertEquals("No menu category found with id 1", exception.getMessage());
    verify(repository, times(1)).existsById(any());
    verify(repository, times(0)).save(any());
  }

  @Test
  void update_menu_category_in_db() {
    MenuCategoryDTO in = new MenuCategoryDTO("category-02");
    MenuCategoryEntity existingEntity = new MenuCategoryEntity(1L, "category-01");
    when(repository.findById(1L)).thenReturn(Optional.of(existingEntity));
    when(repository.save(any())).thenAnswer(i -> i.getArguments()[0]);

    // Test
    MenuCategoryEntity out = service.updateMenuCategory(1L, in);

    // Verify output
    assertEquals(in.getName(), out.getName());
    verify(repository, times(1)).findById(any());
    verify(repository, times(1)).save(any());
  }

  @Test
  void exception_is_thrown_when_updating_wrong_id() {
    MenuCategoryDTO in = new MenuCategoryDTO("category-01");
    when(repository.findById(1L)).thenReturn(Optional.empty());

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.updateMenuCategory(1L, in));

    // Verify output
    assertEquals("No menu category found with id 1", exception.getMessage());
    verify(repository, times(1)).findById(any());
    verify(repository, times(0)).save(any());
  }

  @Test
  void delete_menu_category_by_id() {
    when(repository.existsById(1L)).thenReturn(true);

    // Test
    service.deleteMenuCategory(1L);

    // Verify output
    verify(repository, times(1)).existsById(any());
    verify(repository, times(1)).deleteById(any());
  }

  @Test
  void exception_is_thrown_when_deleting_invalid_id() {
    when(repository.existsById(1L)).thenReturn(false);

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.deleteMenuCategory(1L));

    // Verify output
    assertEquals("No menu category found with id 1", exception.getMessage());
    verify(repository, times(1)).existsById(any());
    verify(repository, times(0)).deleteById(any());
  }
}
