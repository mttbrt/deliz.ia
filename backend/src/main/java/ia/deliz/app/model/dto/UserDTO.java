package ia.deliz.app.model.dto;

import ia.deliz.app.model.validation.NewEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  @NotNull(
      message = "The field 'username' cannot be null.",
      groups = {NewEntity.class})
  @Size(
      min = 1,
      max = 255,
      message = "The field 'username' cannot be empty or longer than 255 characters.",
      groups = {NewEntity.class, Default.class})
  private String username;

  @NotNull(
      message = "The field 'password' cannot be null.",
      groups = {NewEntity.class})
  @Size(
      min = 1,
      max = 255,
      message = "The field 'password' cannot be empty or longer than 255 characters.",
      groups = {NewEntity.class, Default.class})
  private String password;

  @NotNull(
      message = "The field 'roleName' cannot be null.",
      groups = {NewEntity.class})
  @Size(
      min = 1,
      max = 255,
      message = "The field 'roleName' cannot be empty or longer than 255 characters.",
      groups = {NewEntity.class, Default.class})
  private String roleName;
}
