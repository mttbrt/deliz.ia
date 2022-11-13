package ia.deliz.app.model.domain;

import com.google.common.base.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_role")
public class RoleEntity {

  @Id
  @GeneratedValue(generator = "role_seq")
  @SequenceGenerator(name = "role_seq", allocationSize = 1)
  private Long id;

  @Column(unique = true)
  private String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RoleEntity that = (RoleEntity) o;
    return Objects.equal(id, that.id) && Objects.equal(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, name);
  }
}
