package ia.deliz.app.model.domain;

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
}
