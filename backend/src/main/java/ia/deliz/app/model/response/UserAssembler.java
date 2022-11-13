package ia.deliz.app.model.response;

import ia.deliz.app.controller.UserController;
import ia.deliz.app.model.domain.UserEntity;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class UserAssembler extends RepresentationModelAssemblerSupport<UserEntity, UserModel> {

  public UserAssembler() {
    super(UserController.class, UserModel.class);
  }

  @Override
  protected UserModel instantiateModel(UserEntity entity) {
    return new UserModel(entity);
  }

  @Override
  public UserModel toModel(UserEntity entity) {
    return createModelWithId(entity.getId(), entity);
  }
}
