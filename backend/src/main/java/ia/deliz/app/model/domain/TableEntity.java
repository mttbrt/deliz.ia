package ia.deliz.app.model.domain;

import com.google.common.base.Objects;
import ia.deliz.app.model.dto.TableDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_table")
public class TableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name;
  private Short x_pos;
  private Short y_pos;

  public TableEntity(TableDTO table) {
    this.name = table.getName();
    this.x_pos = table.getX_pos();
    this.y_pos = table.getY_pos();
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TableEntity that = (TableEntity) o;
    return Objects.equal(id, that.id)
        && Objects.equal(name, that.name)
        && Objects.equal(x_pos, that.x_pos)
        && Objects.equal(y_pos, that.y_pos);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, name, x_pos, y_pos);
  }
}
