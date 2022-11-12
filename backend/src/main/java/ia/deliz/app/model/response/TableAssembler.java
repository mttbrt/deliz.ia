package ia.deliz.app.model.response;

import ia.deliz.app.controller.TableController;
import ia.deliz.app.model.domain.TableEntity;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class TableAssembler extends RepresentationModelAssemblerSupport<TableEntity, TableModel> {

  public TableAssembler() {
    super(TableController.class, TableModel.class);
  }

  @Override
  protected TableModel instantiateModel(TableEntity tableEntity) {
    return new TableModel(tableEntity);
  }

  @Override
  public TableModel toModel(TableEntity tableEntity) {
    return createModelWithId(tableEntity.getId(), tableEntity);
  }
}
