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

  @CreatedDate
  @Column(nullable = false, updatable = false)
  private LocalDateTime createdDate;

  @LastModifiedDate
  @Column(nullable = false)
  private LocalDateTime lastModifiedDate;
}
