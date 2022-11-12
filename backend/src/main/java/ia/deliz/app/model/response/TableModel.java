package ia.deliz.app.model.response;

import ia.deliz.app.model.domain.TableEntity;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(value = "table", collectionRelation = "tables")
public class TableModel extends RepresentationModel<TableModel> {

  private final String name;
  private final Short x_pos;
  private final Short y_pos;

  public TableModel(TableEntity entity) {
    this.name = entity.getName();
    this.x_pos = entity.getX_pos();
    this.y_pos = entity.getY_pos();
  }
}
