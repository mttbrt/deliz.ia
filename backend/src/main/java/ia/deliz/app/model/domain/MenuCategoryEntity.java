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
@Entity(name = "tbl_menu_category")
public class MenuCategoryEntity {

  @Id
  @GeneratedValue(generator = "menu_category_seq")
  @SequenceGenerator(name = "menu_category_seq", allocationSize = 1)
  private Long id;

  @Column(unique = true)
  private String name;
}
