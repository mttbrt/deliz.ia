package ia.deliz.app.model.dto;

import ia.deliz.app.model.validation.NewEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

@Getter
@Setter
@AllArgsConstructor
public class MenuCategoryDTO {

  @NotNull(
      message = "The field 'name' cannot be null.",
      groups = {NewEntity.class})
  @Size(
      min = 1,
      max = 255,
      message = "The field 'name' cannot be empty or longer than 255 characters.",
      groups = {NewEntity.class, Default.class})
  private String name;
}
