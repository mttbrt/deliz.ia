package ia.deliz.app.service.impl;

import com.google.common.collect.Iterables;
import ia.deliz.app.exception.EntityNotFoundException;
import ia.deliz.app.model.domain.TableEntity;
import ia.deliz.app.model.dto.TableDTO;
import ia.deliz.app.repository.TableRepository;
import ia.deliz.app.service.TableService;
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
class TableServiceImplTest {

  @InjectMocks private TableServiceImpl service;

  @Mock private TableRepository repository;

  @Test
  void get_all_tables_in_db() {
    List<TableEntity> list = new LinkedList<>();
    list.add(new TableEntity(1L, "001", (short) 50, (short) 50));
    list.add(new TableEntity(2L, "002", (short) 50, (short) 50));
    when(repository.findAll()).thenReturn(list);

    // Test
    Iterable<TableEntity> out = service.getTables();

    // Verify output
    assertEquals(2, Iterables.size(out));
    verify(repository, times(1)).findAll();
  }

  @Test
  void get_table_by_id() {
    TableEntity in = new TableEntity(1L, "001", (short) 50, (short) 50);
    when(repository.findById(1L)).thenReturn(Optional.of(in));

    // Test
    TableEntity out = service.getTableById(1L);

    // Verify output
    assertEquals(in, out);
    verify(repository, times(1)).findById(any());
  }

  @Test
  void exception_is_thrown_when_getting_invalid_id() {
    when(repository.findById(1L)).thenReturn(Optional.empty());

    // Test
    Exception exception = assertThrows(EntityNotFoundException.class, () -> {
      service.getTableById(1L);
    });

    // Verify output
    assertEquals("No table found with id 1", exception.getMessage());
    verify(repository, times(1)).findById(any());
  }

  @Test
  void add_new_table() {
    TableDTO in = new TableDTO("001", (short) 50, (short) 50);
    TableEntity entity = new TableEntity(in);
    when(repository.save(entity)).thenReturn(entity);

    // Test
    TableEntity out = service.createTable(in);

    // Verify output
    assertEquals(in.getName(), out.getName());
    assertEquals(in.getX_pos(), out.getX_pos());
    assertEquals(in.getY_pos(), out.getY_pos());
    verify(repository, times(1)).save(any());
  }

  @Test
  void replace_table_in_db() {
    TableDTO in = new TableDTO("001", (short) 50, (short) 50);
    TableEntity entity = new TableEntity(in);
    when(repository.existsById(1L)).thenReturn(true);
    when(repository.save(entity)).thenReturn(entity);

    // Test
    TableEntity out = service.replaceTable(1L, in);

    // Verify output
    assertEquals(in.getName(), out.getName());
    assertEquals(in.getX_pos(), out.getX_pos());
    assertEquals(in.getY_pos(), out.getY_pos());
    verify(repository, times(1)).existsById(any());
    verify(repository, times(1)).save(any());
  }

  @Test
  void exception_is_thrown_when_replacing_wrong_id() {
    TableDTO in = new TableDTO("001", (short) 50, (short) 50);
    when(repository.existsById(1L)).thenReturn(false);

    // Test
    Exception exception = assertThrows(EntityNotFoundException.class, () -> {
      service.replaceTable(1L, in);
    });

    // Verify output
    assertEquals("No table found with id 1", exception.getMessage());
    verify(repository, times(1)).existsById(any());
    verify(repository, times(0)).save(any());
  }

  @Test
  void update_table_in_db() {
    TableDTO in = new TableDTO("002", null, (short) 100);
    TableEntity existingEntity = new TableEntity(1L, "001", (short) 50, (short) 50);
    when(repository.findById(1L)).thenReturn(Optional.of(existingEntity));
    when(repository.save(any())).thenAnswer(i -> i.getArguments()[0]);

    // Test
    TableEntity out = service.updateTable(1L, in);

    // Verify output
    assertEquals(in.getName(), out.getName());
    assertEquals(existingEntity.getX_pos(), out.getX_pos());
    assertEquals(in.getY_pos(), out.getY_pos());
    verify(repository, times(1)).findById(any());
    verify(repository, times(1)).save(any());
  }

  @Test
  void exception_is_thrown_when_updating_wrong_id() {
    TableDTO in = new TableDTO("002", (short) 100, (short) 100);
    when(repository.findById(1L)).thenReturn(Optional.empty());

    // Test
    Exception exception = assertThrows(EntityNotFoundException.class, () -> {
      service.updateTable(1L, in);
    });

    // Verify output
    assertEquals("No table found with id 1", exception.getMessage());
    verify(repository, times(1)).findById(any());
    verify(repository, times(0)).save(any());
  }

  @Test
  void delete_table_by_id() {
    when(repository.existsById(1L)).thenReturn(true);

    // Test
    service.deleteTable(1L);

    // Verify output
    verify(repository, times(1)).existsById(any());
    verify(repository, times(1)).deleteById(any());
  }

  @Test
  void exception_is_thrown_when_deleting_invalid_id() {
    when(repository.existsById(1L)).thenReturn(false);

    // Test
    Exception exception = assertThrows(EntityNotFoundException.class, () -> {
      service.deleteTable(1L);
    });

    // Verify output
    assertEquals("No table found with id 1", exception.getMessage());
    verify(repository, times(1)).existsById(any());
    verify(repository, times(0)).deleteById(any());
  }
}
