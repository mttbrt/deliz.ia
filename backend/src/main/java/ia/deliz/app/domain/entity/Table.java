package ia.deliz.app.domain.entity;

import ia.deliz.app.domain.model.TableModel;
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
public class Table {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name;
  private String description;
  private Short x_pos;
  private Short y_pos;

  public Table(TableModel model) {
    this.name = model.getName();
    this.description = model.getDescription();
    this.x_pos = model.getX_pos();
    this.y_pos = model.getY_pos();
  }

  public Table updateNonNullAttributes(TableModel table) {
    if (table.getName() != null) {
      this.name = table.getName();
    }
    if (table.getDescription() != null) {
      this.description = table.getDescription();
    }
    if (table.getX_pos() != null) {
      this.x_pos = table.getY_pos();
    }
    if (table.getY_pos() != null) {
      this.y_pos = table.getY_pos();
    }
    return this;
  }
}
