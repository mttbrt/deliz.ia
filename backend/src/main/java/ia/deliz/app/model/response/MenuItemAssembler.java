package ia.deliz.app.model.response;

import ia.deliz.app.controller.MenuItemController;
import ia.deliz.app.model.domain.MenuItemEntity;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class MenuItemAssembler
    extends RepresentationModelAssemblerSupport<MenuItemEntity, MenuItemModel> {

  public MenuItemAssembler() {
    super(MenuItemController.class, MenuItemModel.class);
  }

  @Override
  protected MenuItemModel instantiateModel(MenuItemEntity entity) {
    return new MenuItemModel(entity);
  }

  @Override
  public MenuItemModel toModel(MenuItemEntity entity) {
    return createModelWithId(entity.getId(), entity);
  }
}
