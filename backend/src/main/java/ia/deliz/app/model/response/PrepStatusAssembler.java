package ia.deliz.app.model.response;

import ia.deliz.app.controller.PrepStatusController;
import ia.deliz.app.model.domain.PrepStatusEntity;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class PrepStatusAssembler extends RepresentationModelAssemblerSupport<PrepStatusEntity, PrepStatusModel> {

  public PrepStatusAssembler() {
    super(PrepStatusController.class, PrepStatusModel.class);
  }

  @Override
  protected PrepStatusModel instantiateModel(PrepStatusEntity entity) {
    return new PrepStatusModel(entity);
  }

  @Override
  public PrepStatusModel toModel(PrepStatusEntity entity) {
    return createModelWithId(entity.getId(), entity);
  }
}
