package ia.deliz.app.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdDate;

  @LastModifiedDate
  @Column(nullable = false)
  private LocalDateTime lastModifiedDate;
}
