package ia.deliz.app.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

  @JsonIgnore private String password;

  @ManyToOne(fetch = FetchType.LAZY)
  private RoleEntity role;
}
