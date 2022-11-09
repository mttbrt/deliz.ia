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

  public TableModel(TableEntity tableEntity) {
    this.name = tableEntity.getName();
    this.x_pos = tableEntity.getX_pos();
    this.y_pos = tableEntity.getY_pos();
  }
}
