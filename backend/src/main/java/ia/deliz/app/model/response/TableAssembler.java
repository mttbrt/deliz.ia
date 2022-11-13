package ia.deliz.app.model.response;

import ia.deliz.app.controller.TableController;
import ia.deliz.app.model.domain.TableEntity;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class TableAssembler extends RepresentationModelAssemblerSupport<TableEntity, TableModel> {

  public TableAssembler() {
    super(TableController.class, TableModel.class);
  }

  @Override
  protected TableModel instantiateModel(TableEntity entity) {
    return new TableModel(entity);
  }

  @Override
  public TableModel toModel(TableEntity entity) {
    return createModelWithId(entity.getId(), entity);
  }
}
