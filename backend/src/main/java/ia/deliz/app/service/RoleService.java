package ia.deliz.app.service;

import ia.deliz.app.model.domain.RoleEntity;

public interface RoleService {

  Iterable<RoleEntity> getRoles();

  RoleEntity getRoleById(Long id);
}
