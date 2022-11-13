package ia.deliz.app.model.response;

import ia.deliz.app.model.domain.UserEntity;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(value = "user", collectionRelation = "users")
public class UserModel extends RepresentationModel<UserModel> {

  private static final RoleAssembler assembler = new RoleAssembler();
  private final String username;
  private final RoleModel role;

  public UserModel(UserEntity entity) {
    this.username = entity.getUsername();
    this.role = assembler.toModel(entity.getRole());
  }
}
