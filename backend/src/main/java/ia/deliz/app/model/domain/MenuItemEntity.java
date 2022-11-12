package ia.deliz.app.model.domain;

import com.google.common.base.Objects;
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
@Entity(name = "tbl_menu_item")
public class MenuItemEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(unique = true)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  private Float price;
  @ManyToOne private MenuCategoryEntity category;

  public MenuItemEntity(MenuItemDTO menuItem) {
    this.name = menuItem.getName();
    this.description = menuItem.getDescription();
    this.price = menuItem.getPrice();
    this.category = menuItem.getCategory();
  }

  public MenuItemEntity updateNonNullAttributes(MenuItemDTO menuItem) {
    if (menuItem.getName() != null) {
      this.name = menuItem.getName();
    }
    if (menuItem.getDescription() != null) {
      this.description = menuItem.getDescription();
    }
    if (menuItem.getPrice() != null) {
      this.price = menuItem.getPrice();
    }
    if (menuItem.getCategory() != null) {
      this.category = menuItem.getCategory();
    }
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    MenuItemEntity that = (MenuItemEntity) o;
    return Objects.equal(id, that.id)
        && Objects.equal(name, that.name)
        && Objects.equal(description, that.description)
        && Objects.equal(price, that.price)
        && Objects.equal(category, that.category);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, name, description, price, category);
  }
}
