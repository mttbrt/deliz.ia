package ia.deliz.app.model.domain;

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
}
