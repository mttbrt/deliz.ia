package ia.deliz.app.model.domain;

import ia.deliz.app.model.dto.TableDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "tbl_table")
public class TableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name;
  private Short x_pos;
  private Short y_pos;

  public TableEntity(TableDTO model) {
    this.name = model.getName();
    this.x_pos = model.getX_pos();
    this.y_pos = model.getY_pos();
  }

  public TableEntity updateNonNullAttributes(TableDTO table) {
    if (table.getName() != null) {
      this.name = table.getName();
    }
    if (table.getX_pos() != null) {
      this.x_pos = table.getX_pos();
    }
    if (table.getY_pos() != null) {
      this.y_pos = table.getY_pos();
    }
    return this;
  }
}
