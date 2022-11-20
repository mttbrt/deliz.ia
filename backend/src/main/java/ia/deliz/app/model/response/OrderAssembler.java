package ia.deliz.app.model.response;

import ia.deliz.app.controller.OrderController;
import ia.deliz.app.model.domain.OrderEntity;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class OrderAssembler extends RepresentationModelAssemblerSupport<OrderEntity, OrderModel> {

  public OrderAssembler() {
    super(OrderController.class, OrderModel.class);
  }

  @Override
  protected OrderModel instantiateModel(OrderEntity entity) {
    return new OrderModel(); // TODO
  }

  @Override
  public OrderModel toModel(OrderEntity entity) {
    return createModelWithId(entity.getId(), entity);
  }
}
