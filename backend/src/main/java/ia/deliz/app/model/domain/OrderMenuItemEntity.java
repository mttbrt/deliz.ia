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
@Entity(name = "tbl_order_menu_item")
public class OrderMenuItemEntity {

  @Id
  @GeneratedValue(generator = "order_menu_item_seq")
  @SequenceGenerator(name = "order_menu_item_seq", allocationSize = 1)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private OrderEntity order;

  @ManyToOne(fetch = FetchType.LAZY)
  private MenuItemEntity item;

  @ManyToOne(fetch = FetchType.LAZY)
  private PrepStatusEntity status;
}
