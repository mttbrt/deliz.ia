package ia.deliz.app.model.domain;

import com.google.common.base.Objects;
import ia.deliz.app.model.dto.MenuCategoryDTO;
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
  @GeneratedValue(generator = "menu_category_seq")
  @SequenceGenerator(name = "menu_category_seq", allocationSize = 1)
  private Long id;

  @Column(unique = true)
  private String name;

  public MenuCategoryEntity(MenuCategoryDTO dto) {
    this.name = dto.getName();
  }

  public MenuCategoryEntity updateNonNullAttributes(MenuCategoryDTO dto) {
    if (dto.getName() != null) {
      this.name = dto.getName();
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
