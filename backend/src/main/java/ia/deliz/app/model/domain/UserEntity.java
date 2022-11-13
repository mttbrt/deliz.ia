package ia.deliz.app.model.domain;

import com.google.common.base.Objects;
import ia.deliz.app.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_user")
public class UserEntity {

  @Id
  @GeneratedValue(generator = "user_seq")
  @SequenceGenerator(name = "user_seq", allocationSize = 1)
  private Long id;

  @Column(unique = true)
  private String username;

  private String password;

  @ManyToOne(fetch = FetchType.LAZY)
  private RoleEntity role;

  public UserEntity(UserDTO dto, RoleEntity role) {
    this.username = dto.getUsername();
    this.password = dto.getPassword();
    this.role = role;
  }

  public UserEntity updateNonNullAttributes(UserDTO dto, RoleEntity role) {
    if (dto.getUsername() != null) {
      this.username = dto.getUsername();
    }
    if (dto.getPassword() != null) {
      this.password = dto.getPassword();
    }
    if (role != null) {
      this.role = role;
    }
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UserEntity that = (UserEntity) o;
    return Objects.equal(id, that.id)
        && Objects.equal(username, that.username)
        && Objects.equal(password, that.password)
        && Objects.equal(role, that.role);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id, username, password, role);
  }
}
