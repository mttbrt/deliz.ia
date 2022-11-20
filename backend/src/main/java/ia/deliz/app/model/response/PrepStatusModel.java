package ia.deliz.app.model.response;

import ia.deliz.app.model.domain.PrepStatusEntity;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(value = "prep-status", collectionRelation = "prep-statuses")
public class PrepStatusModel extends RepresentationModel<PrepStatusModel> {

  private final String name;

  public PrepStatusModel(PrepStatusEntity entity) {
    this.name = entity.getName();
  }
}
