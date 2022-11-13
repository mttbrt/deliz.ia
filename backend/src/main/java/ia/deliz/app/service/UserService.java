package ia.deliz.app.service;

import ia.deliz.app.model.domain.UserEntity;
import ia.deliz.app.model.dto.UserDTO;

public interface UserService {

  Iterable<UserEntity> getUsers();

  UserEntity getUserById(Long id);

  UserEntity createUser(UserDTO dto);

  UserEntity replaceUser(Long id, UserDTO dto);

  UserEntity updateUser(Long id, UserDTO dto);

  void deleteUser(Long id);
}
