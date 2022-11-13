package ia.deliz.app.service.impl;

import ia.deliz.app.exception.EntityNotFoundException;
import ia.deliz.app.model.domain.RoleEntity;
import ia.deliz.app.repository.RoleRepository;
import ia.deliz.app.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

  @Autowired private RoleRepository roleRepository;

  @Override
  public Iterable<RoleEntity> getRoles() {
    return roleRepository.findAll();
  }

  @Override
  public RoleEntity getRoleById(Long id) {
    Optional<RoleEntity> existingEntity = roleRepository.findById(id);
    if (existingEntity.isEmpty()) {
      throw new EntityNotFoundException(String.format("No role found with id %d", id));
    }

    return existingEntity.get();
  }
}
