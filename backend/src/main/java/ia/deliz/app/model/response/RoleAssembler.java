package ia.deliz.app.model.response;

import ia.deliz.app.controller.RoleController;
import ia.deliz.app.model.domain.RoleEntity;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class RoleAssembler extends RepresentationModelAssemblerSupport<RoleEntity, RoleModel> {

  public RoleAssembler() {
    super(RoleController.class, RoleModel.class);
  }

  @Override
  protected RoleModel instantiateModel(RoleEntity entity) {
    return new RoleModel(entity);
  }

  @Override
  public RoleModel toModel(RoleEntity entity) {
    return createModelWithId(entity.getId(), entity);
  }
}
