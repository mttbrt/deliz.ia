package ia.deliz.app.domain.model;

import ia.deliz.app.domain.entity.Table;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(value = "table", collectionRelation = "tables")
public class TableModel extends RepresentationModel<TableModel> {

  private final String name;
  private final String description;
  private final Short x_pos;
  private final Short y_pos;

  public TableModel(Table table) {
    this.name = table.getName();
    this.description = table.getDescription();
    this.x_pos = table.getX_pos();
    this.y_pos = table.getY_pos();
  }
}
