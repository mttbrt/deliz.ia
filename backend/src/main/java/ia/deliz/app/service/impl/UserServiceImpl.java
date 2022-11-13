package ia.deliz.app.service.impl;

import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import ia.deliz.app.exception.EntityNotFoundException;
import ia.deliz.app.model.domain.RoleEntity;
import ia.deliz.app.model.domain.UserEntity;
import ia.deliz.app.model.dto.UserDTO;
import ia.deliz.app.repository.RoleRepository;
import ia.deliz.app.repository.UserRepository;
import ia.deliz.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;
  @Autowired private RoleRepository roleRepository;

  @Override
  public Iterable<UserEntity> getUsers() {
    return userRepository.findAll();
  }

  @Override
  public UserEntity getUserById(Long id) {
    Optional<UserEntity> existingEntity = userRepository.findById(id);
    if (existingEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No user found with id %d", id));
    }

    return existingEntity.get();
  }

  @Override
  public UserEntity createUser(UserDTO dto) {
    Optional<RoleEntity> roleEntity = roleRepository.findFirstByName(dto.getRoleName());
    if (roleEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No role found with name %s", dto.getRoleName()));
    }

    UserEntity entity = new UserEntity(dto, roleEntity.get());
    return userRepository.save(entity);
  }

  @Override
  public UserEntity replaceUser(Long id, UserDTO dto) {
    if (!userRepository.existsById(id)) {
      throw new EntityNotFoundException(String.format("No user found with id %d", id));
    }

    Optional<RoleEntity> roleEntity = roleRepository.findFirstByName(dto.getRoleName());
    if (roleEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No role found with name %s", dto.getRoleName()));
    }

    UserEntity replacedEntity = new UserEntity(dto, roleEntity.get());
    return userRepository.save(replacedEntity);
  }

  @Override
  public UserEntity updateUser(Long id, UserDTO dto) {
    Optional<UserEntity> existingEntity = userRepository.findById(id);
    if (existingEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No user found with id %d", id));
    }

    Optional<RoleEntity> roleEntity = roleRepository.findFirstByName(dto.getRoleName());
    if (roleEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No role found with name %s", dto.getRoleName()));
    }

    UserEntity updatedEntity = existingEntity.get().updateNonNullAttributes(dto, roleEntity.get());
    return userRepository.save(updatedEntity);
  }

  @Override
  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new EntityNotFoundException(String.format("No user found with id %d", id));
    }

    userRepository.deleteById(id);
  }
}
