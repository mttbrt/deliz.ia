package ia.deliz.app.model.domain;

import com.google.common.base.Objects;
import ia.deliz.app.model.dto.MenuCategoryDTO;
import ia.deliz.app.model.dto.MenuItemDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_menu_category")
public class MenuCategoryEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(unique = true)
  private String name;

  public MenuCategoryEntity(MenuCategoryDTO menuCategory) {
    this.name = menuCategory.getName();
  }

  public MenuCategoryEntity updateNonNullAttributes(MenuItemDTO menuItem) {
    if (menuItem.getName() != null) {
      this.name = menuItem.getName();
    }
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MenuCategoryEntity that = (MenuCategoryEntity) o;
    return Objects.equal(id, that.id) && Objects.equal(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, name);
  }
}
