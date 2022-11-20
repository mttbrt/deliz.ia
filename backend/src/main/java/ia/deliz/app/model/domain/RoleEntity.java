package ia.deliz.app.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

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
}
