package ia.deliz.app.model.response;

import ia.deliz.app.controller.OrderMenuItemController;
import ia.deliz.app.model.domain.OrderMenuItemEntity;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class OrderMenuItemAssembler extends RepresentationModelAssemblerSupport<OrderMenuItemEntity, OrderMenuItemModel> {

  public OrderMenuItemAssembler() {
    super(OrderMenuItemController.class, OrderMenuItemModel.class);
  }

  @Override
  protected OrderMenuItemModel instantiateModel(OrderMenuItemEntity entity) {
    return new OrderMenuItemModel(); // TODO
  }

  @Override
  public OrderMenuItemModel toModel(OrderMenuItemEntity entity) {
    return createModelWithId(entity.getId(), entity);
  }
}
