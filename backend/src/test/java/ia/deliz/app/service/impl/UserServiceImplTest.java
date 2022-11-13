package ia.deliz.app.service.impl;

import com.google.common.collect.Iterables;
import ia.deliz.app.exception.EntityNotFoundException;
import ia.deliz.app.model.domain.RoleEntity;
import ia.deliz.app.model.domain.UserEntity;
import ia.deliz.app.model.dto.UserDTO;
import ia.deliz.app.repository.RoleRepository;
import ia.deliz.app.repository.UserRepository;
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
class UserServiceImplTest {

  @InjectMocks private UserServiceImpl service;

  @Mock private UserRepository userRepository;
  @Mock private RoleRepository roleRepository;

  @Test
  void get_all_users_in_db() {
    RoleEntity role = new RoleEntity(1L, "ROLE_ADMIN");
    List<UserEntity> list = new LinkedList<>();
    list.add(new UserEntity(1L, "user-01", "psw", role));
    list.add(new UserEntity(2L, "user-02", "psw", role));
    when(userRepository.findAll()).thenReturn(list);

    // Test
    Iterable<UserEntity> out = service.getUsers();

    // Verify output
    assertEquals(2, Iterables.size(out));
    verify(userRepository, times(1)).findAll();
  }

  @Test
  void get_user_by_id() {
    RoleEntity role = new RoleEntity(1L, "ROLE_ADMIN");
    UserEntity in = new UserEntity(1L, "user-01", "psw", role);
    when(userRepository.findById(1L)).thenReturn(Optional.of(in));

    // Test
    UserEntity out = service.getUserById(1L);

    // Verify output
    assertEquals(in, out);
    verify(userRepository, times(1)).findById(any());
  }

  @Test
  void exception_is_thrown_when_getting_invalid_id() {
    when(userRepository.findById(1L)).thenReturn(Optional.empty());

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.getUserById(1L));

    // Verify output
    assertEquals("No user found with id 1", exception.getMessage());
    verify(userRepository, times(1)).findById(any());
  }

  @Test
  void add_new_user() {
    UserDTO in = new UserDTO("user-01", "psw", "ROLE_ADMIN");
    RoleEntity role = new RoleEntity(1L, "ROLE_ADMIN");
    UserEntity entity = new UserEntity(in, role);
    when(roleRepository.findFirstByName("ROLE_ADMIN")).thenReturn(Optional.of(role));
    when(userRepository.save(entity)).thenReturn(entity);

    // Test
    UserEntity out = service.createUser(in);

    // Verify output
    assertEquals(in.getUsername(), out.getUsername());
    assertEquals(in.getPassword(), out.getPassword());
    assertEquals(in.getRoleName(), out.getRole().getName());
    verify(roleRepository, times(1)).findFirstByName(any());
    verify(userRepository, times(1)).save(any());
  }

  @Test
  void exception_is_thrown_when_adding_new_user_with_wrong_role_id() {
    UserDTO in = new UserDTO("user-01", "psw", "ROLE_ADMIN");
    when(roleRepository.findFirstByName("ROLE_ADMIN")).thenReturn(Optional.empty());

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.createUser(in));

    // Verify output
    assertEquals("No role found with name ROLE_ADMIN", exception.getMessage());
    verify(roleRepository, times(1)).findFirstByName(any());
    verify(userRepository, times(0)).save(any());
  }

  @Test
  void replace_user_in_db() {
    UserDTO in = new UserDTO("user-01", "psw", "ROLE_ADMIN");
    RoleEntity role = new RoleEntity(1L, "ROLE_ADMIN");
    UserEntity entity = new UserEntity(in, role);
    when(userRepository.existsById(1L)).thenReturn(true);
    when(roleRepository.findFirstByName("ROLE_ADMIN")).thenReturn(Optional.of(role));
    when(userRepository.save(entity)).thenReturn(entity);

    // Test
    UserEntity out = service.replaceUser(1L, in);

    // Verify output
    assertEquals(in.getUsername(), out.getUsername());
    assertEquals(in.getPassword(), out.getPassword());
    assertEquals(in.getRoleName(), out.getRole().getName());
    verify(userRepository, times(1)).existsById(any());
    verify(roleRepository, times(1)).findFirstByName(any());
    verify(userRepository, times(1)).save(any());
  }

  @Test
  void exception_is_thrown_when_replacing_wrong_id() {
    UserDTO in = new UserDTO("user-01", "psw", "ROLE_ADMIN");
    when(userRepository.existsById(1L)).thenReturn(false);

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.replaceUser(1L, in));

    // Verify output
    assertEquals("No user found with id 1", exception.getMessage());
    verify(userRepository, times(1)).existsById(any());
    verify(userRepository, times(0)).save(any());
  }

  @Test
  void exception_is_thrown_when_replacing_new_user_with_wrong_role_id() {
    UserDTO in = new UserDTO("user-01", "psw", "ROLE_ADMIN");
    when(userRepository.existsById(1L)).thenReturn(true);
    when(roleRepository.findFirstByName("ROLE_ADMIN")).thenReturn(Optional.empty());

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.replaceUser(1L, in));

    // Verify output
    assertEquals("No role found with name ROLE_ADMIN", exception.getMessage());
    verify(userRepository, times(1)).existsById(any());
    verify(roleRepository, times(1)).findFirstByName(any());
    verify(userRepository, times(0)).save(any());
  }

  @Test
  void update_user_in_db() {
    RoleEntity role = new RoleEntity(1L, "ROLE_ADMIN");
    UserDTO in = new UserDTO("user-01", "psw", "ROLE_ADMIN");
    UserEntity existingEntity = new UserEntity(1L, "user-01", "psw", role);
    when(userRepository.findById(1L)).thenReturn(Optional.of(existingEntity));
    when(roleRepository.findFirstByName("ROLE_ADMIN")).thenReturn(Optional.of(role));
    when(userRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

    // Test
    UserEntity out = service.updateUser(1L, in);

    // Verify output
    assertEquals(in.getUsername(), out.getUsername());
    assertEquals(in.getPassword(), out.getPassword());
    assertEquals(in.getRoleName(), out.getRole().getName());
    verify(userRepository, times(1)).findById(any());
    verify(roleRepository, times(1)).findFirstByName(any());
    verify(userRepository, times(1)).save(any());
  }

  @Test
  void exception_is_thrown_when_updating_wrong_id() {
    UserDTO in = new UserDTO("user-01", "psw", "ROLE_ADMIN");
    when(userRepository.findById(1L)).thenReturn(Optional.empty());

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.updateUser(1L, in));

    // Verify output
    assertEquals("No user found with id 1", exception.getMessage());
    verify(userRepository, times(1)).findById(any());
    verify(userRepository, times(0)).save(any());
  }

  @Test
  void exception_is_thrown_when_updating_new_user_with_wrong_role_id() {
    RoleEntity role = new RoleEntity(1L, "ROLE_ADMIN");
    UserDTO in = new UserDTO("user-01", "psw1", "ROLE_ADMIN");
    UserEntity existingEntity = new UserEntity(1L, "user-01", "psw", role);
    when(userRepository.findById(1L)).thenReturn(Optional.of(existingEntity));
    when(roleRepository.findFirstByName("ROLE_ADMIN")).thenReturn(Optional.empty());

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.updateUser(1L, in));

    // Verify output
    assertEquals("No role found with name ROLE_ADMIN", exception.getMessage());
    verify(userRepository, times(1)).findById(any());
    verify(roleRepository, times(1)).findFirstByName(any());
    verify(userRepository, times(0)).save(any());
  }

  @Test
  void delete_user_by_id() {
    when(userRepository.existsById(1L)).thenReturn(true);

    // Test
    service.deleteUser(1L);

    // Verify output
    verify(userRepository, times(1)).existsById(any());
    verify(userRepository, times(1)).deleteById(any());
  }

  @Test
  void exception_is_thrown_when_deleting_invalid_id() {
    when(userRepository.existsById(1L)).thenReturn(false);

    // Test
    Exception exception =
        assertThrows(EntityNotFoundException.class, () -> service.deleteUser(1L));

    // Verify output
    assertEquals("No user found with id 1", exception.getMessage());
    verify(userRepository, times(1)).existsById(any());
    verify(userRepository, times(0)).deleteById(any());
  }
}
