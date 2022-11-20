package ia.deliz.app.model.domain;

import com.google.common.base.Objects;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OrderMenuItemEntity that = (OrderMenuItemEntity) o;
    return Objects.equal(id, that.id)
        && Objects.equal(order, that.order)
        && Objects.equal(item, that.item)
        && Objects.equal(status, that.status);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, order, item, status);
  }
}
