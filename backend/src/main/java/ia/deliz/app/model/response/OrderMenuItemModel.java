package ia.deliz.app.model.response;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(value = "order-menu-item", collectionRelation = "order-menu-items")
public class OrderMenuItemModel extends RepresentationModel<OrderMenuItemModel> {
  // TODO
}
