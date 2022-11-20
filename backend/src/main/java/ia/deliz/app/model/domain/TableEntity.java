package ia.deliz.app.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_table")
public class TableEntity {

  @Id
  @GeneratedValue(generator = "table_seq")
  @SequenceGenerator(name = "table_seq", allocationSize = 1)
  private Long id;

  private String name;
  private Short x_pos;
  private Short y_pos;
}
