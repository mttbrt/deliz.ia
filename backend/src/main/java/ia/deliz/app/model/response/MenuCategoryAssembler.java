package ia.deliz.app.model.response;

import ia.deliz.app.controller.MenuCategoryController;
import ia.deliz.app.model.domain.MenuCategoryEntity;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public class MenuCategoryAssembler
    extends RepresentationModelAssemblerSupport<MenuCategoryEntity, MenuCategoryModel> {

  public MenuCategoryAssembler() {
    super(MenuCategoryController.class, MenuCategoryModel.class);
  }

  @Override
  protected MenuCategoryModel instantiateModel(MenuCategoryEntity entity) {
    return new MenuCategoryModel(entity);
  }

  @Override
  public MenuCategoryModel toModel(MenuCategoryEntity entity) {
    return createModelWithId(entity.getId(), entity);
  }
}
