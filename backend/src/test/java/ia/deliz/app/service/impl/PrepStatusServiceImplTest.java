package ia.deliz.app.service.impl;

import com.google.common.collect.Iterables;
import ia.deliz.app.exception.EntityNotFoundException;
import ia.deliz.app.model.domain.PrepStatusEntity;
import ia.deliz.app.repository.PrepStatusRepository;
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
class PrepStatusServiceImplTest {

  @InjectMocks private PrepStatusServiceImpl service;

  @Mock private PrepStatusRepository repository;

  @Test
  void get_all_prepStatuses_in_db() {
    List<PrepStatusEntity> list = new LinkedList<>();
    list.add(new PrepStatusEntity(1L, "ORDERED"));
    list.add(new PrepStatusEntity(2L, "PREPARING"));
    list.add(new PrepStatusEntity(3L, "READY"));
    list.add(new PrepStatusEntity(4L, "SERVED"));
    when(repository.findAll()).thenReturn(list);

    // Test
    Iterable<PrepStatusEntity> out = service.getPrepStatuses();

    // Verify output
    assertEquals(3, Iterables.size(out));
    verify(repository, times(1)).findAll();
  }

  @Test
  void get_prepStatus_by_id() {
    PrepStatusEntity in = new PrepStatusEntity(1L, "ORDERED");
    when(repository.findById(1L)).thenReturn(Optional.of(in));

    // Test
    PrepStatusEntity out = service.getPrepStatusById(1L);

    // Verify output
    assertEquals(in, out);
    verify(repository, times(1)).findById(any());
  }

  @Test
  void exception_is_thrown_when_getting_invalid_id() {
    when(repository.findById(1L)).thenReturn(Optional.empty());

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.getPrepStatusById(1L));

    // Verify output
    assertEquals("No preparation status found with id 1", exception.getMessage());
    verify(repository, times(1)).findById(any());
  }

}
