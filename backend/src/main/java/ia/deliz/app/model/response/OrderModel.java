package ia.deliz.app.model.response;

import ia.deliz.app.model.domain.OrderEntity;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(value = "order", collectionRelation = "orders")
public class OrderModel extends RepresentationModel<OrderModel> {
  // TODO
}
