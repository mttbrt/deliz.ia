package ia.deliz.app.model.response;

import ia.deliz.app.model.domain.MenuCategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Getter
@AllArgsConstructor
@Relation(value = "menu-category", collectionRelation = "menu-categories")
public class MenuCategoryModel extends RepresentationModel<MenuCategoryModel> {

  private final String name;

  public MenuCategoryModel(MenuCategoryEntity entity) {
    this.name = entity.getName();
  }
}
