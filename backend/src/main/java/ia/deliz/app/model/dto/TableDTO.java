package ia.deliz.app.model.dto;

import ia.deliz.app.model.validation.NewEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

@Getter
@Setter
@AllArgsConstructor
public class TableDTO {

  @NotNull(message = "The field 'name' cannot be null.", groups = {NewEntity.class})
  @Size(
      min = 1,
      max = 255,
      message = "The field 'name' cannot be empty or longer than 255 characters.", groups = {NewEntity.class, Default.class})
  @Pattern(
      regexp = "^[-_a-zA-Z0-9]*$",
      message = "The field 'name' can only contain: letters, numbers, hyphens and underscores.", groups = {NewEntity.class, Default.class})
  private String name;

  @NotNull(message = "The field 'x_pos' cannot be null.", groups = NewEntity.class)
  @Range(min = 1, max = 3840, message = "The field 'x_pos' must be in range [1, 3840] (inclusive).", groups = {NewEntity.class, Default.class})
  private Short x_pos;

  @NotNull(message = "The field 'y_pos' cannot be null.", groups = NewEntity.class)
  @Range(min = 1, max = 2160, message = "The field 'y_pos' must be in range [1, 2160] (inclusive).", groups = {NewEntity.class, Default.class})
  private Short y_pos;
}
