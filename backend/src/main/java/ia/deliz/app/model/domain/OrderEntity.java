package ia.deliz.app.model.domain;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_order")
public class OrderEntity {

  @Id
  @GeneratedValue(generator = "order_seq")
  @SequenceGenerator(name = "order_seq", allocationSize = 1)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private TableEntity table;

  @ManyToOne(fetch = FetchType.LAZY)
  private UserEntity takenBy;

  private Boolean open = true;

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
  private List<OrderMenuItemEntity> menuItems;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OrderEntity that = (OrderEntity) o;
    return Objects.equal(id, that.id)
        && Objects.equal(table, that.table)
        && Objects.equal(takenBy, that.takenBy)
        && Objects.equal(open, that.open);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, table, takenBy, open);
  }
}
