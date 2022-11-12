package ia.deliz.app.model.response;

import ia.deliz.app.model.domain.MenuItemEntity;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(value = "menu-item", collectionRelation = "menu-items")
public class MenuItemModel extends RepresentationModel<MenuItemModel> {

  private static final MenuCategoryAssembler assembler = new MenuCategoryAssembler();
  private final String name;
  private final String description;
  private final Float price;
  private final MenuCategoryModel category;

  public MenuItemModel(MenuItemEntity entity) {
    this.name = entity.getName();
    this.description = entity.getDescription();
    this.price = entity.getPrice();
    this.category = assembler.toModel(entity.getCategory());
  }
}
