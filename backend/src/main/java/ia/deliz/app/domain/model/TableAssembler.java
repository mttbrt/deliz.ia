package ia.deliz.app.domain.model;

import ia.deliz.app.controller.TableController;
import ia.deliz.app.domain.entity.Table;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class TableAssembler extends RepresentationModelAssemblerSupport<Table, TableModel> {

  public TableAssembler() {
    super(TableController.class, TableModel.class);
  }

  @Override
  protected TableModel instantiateModel(Table table) {
    return new TableModel(table);
  }

  @Override
  public TableModel toModel(Table table) {
    return createModelWithId(table.getId(), table);
  }
}
