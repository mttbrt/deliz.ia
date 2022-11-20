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
@Entity(name = "tbl_prep_status")
public class PrepStatusEntity {

  @Id
  @GeneratedValue(generator = "prep_status_seq")
  @SequenceGenerator(name = "prep_status_seq", allocationSize = 1)
  private Long id;

  @Column(unique = true)
  private String name;
}
