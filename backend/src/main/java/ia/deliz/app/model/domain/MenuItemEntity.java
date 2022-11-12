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
  @GeneratedValue(generator = "menu_item_seq")
  @SequenceGenerator(name = "menu_item_seq", allocationSize = 1)
  private Long id;

  @Column(unique = true)
  private String name;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Column(precision = 6, scale = 2)
  private Float price;

  @ManyToOne(fetch = FetchType.LAZY)
  private MenuCategoryEntity category;

  public MenuItemEntity(MenuItemDTO dto, MenuCategoryEntity category) {
    this.name = dto.getName();
    this.description = dto.getDescription();
    this.price = dto.getPrice();
    this.category = category;
  }

  public MenuItemEntity updateNonNullAttributes(MenuItemDTO dto, MenuCategoryEntity category) {
    if (dto.getName() != null) {
      this.name = dto.getName();
    }
    if (dto.getDescription() != null) {
      this.description = dto.getDescription();
    }
    if (dto.getPrice() != null) {
      this.price = dto.getPrice();
    }
    if (category != null) {
      this.category = category;
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
