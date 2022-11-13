package ia.deliz.app.service.impl;

import com.google.common.collect.Iterables;
import ia.deliz.app.exception.EntityNotFoundException;
import ia.deliz.app.model.domain.RoleEntity;
import ia.deliz.app.model.domain.TableEntity;
import ia.deliz.app.model.dto.TableDTO;
import ia.deliz.app.repository.RoleRepository;
import ia.deliz.app.repository.TableRepository;
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
class RoleServiceImplTest {

  @InjectMocks private RoleServiceImpl service;

  @Mock private RoleRepository repository;

  @Test
  void get_all_roles_in_db() {
    List<RoleEntity> list = new LinkedList<>();
    list.add(new RoleEntity(1L, "ROLE_ADMIN"));
    list.add(new RoleEntity(2L, "ROLE_FOH"));
    list.add(new RoleEntity(3L, "ROLE_BOH"));
    when(repository.findAll()).thenReturn(list);

    // Test
    Iterable<RoleEntity> out = service.getRoles();

    // Verify output
    assertEquals(3, Iterables.size(out));
    verify(repository, times(1)).findAll();
  }

  @Test
  void get_role_by_id() {
    RoleEntity in = new RoleEntity(1L, "ROLE_ADMIN");
    when(repository.findById(1L)).thenReturn(Optional.of(in));

    // Test
    RoleEntity out = service.getRoleById(1L);

    // Verify output
    assertEquals(in, out);
    verify(repository, times(1)).findById(any());
  }

  @Test
  void exception_is_thrown_when_getting_invalid_id() {
    when(repository.findById(1L)).thenReturn(Optional.empty());

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.getRoleById(1L));

    // Verify output
    assertEquals("No role found with id 1", exception.getMessage());
    verify(repository, times(1)).findById(any());
  }

}
