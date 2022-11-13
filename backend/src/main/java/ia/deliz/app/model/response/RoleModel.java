package ia.deliz.app.model.response;

import ia.deliz.app.model.domain.RoleEntity;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(value = "role", collectionRelation = "roles")
public class RoleModel extends RepresentationModel<RoleModel> {

  private final String name;

  public RoleModel(RoleEntity entity) {
    this.name = entity.getName();
  }
}
