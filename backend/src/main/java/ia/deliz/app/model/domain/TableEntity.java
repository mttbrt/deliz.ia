package ia.deliz.app.model.domain;

import com.google.common.base.Objects;
import ia.deliz.app.model.dto.TableDTO;
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

  public TableEntity(TableDTO dto) {
    this.name = dto.getName();
    this.x_pos = dto.getX_pos();
    this.y_pos = dto.getY_pos();
  }

  public TableEntity updateNonNullAttributes(TableDTO dto) {
    if (dto.getName() != null) {
      this.name = dto.getName();
    }
    if (dto.getX_pos() != null) {
      this.x_pos = dto.getX_pos();
    }
    if (dto.getY_pos() != null) {
      this.y_pos = dto.getY_pos();
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
