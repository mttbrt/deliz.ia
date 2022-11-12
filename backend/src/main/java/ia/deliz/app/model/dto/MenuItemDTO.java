package ia.deliz.app.model.dto;

import ia.deliz.app.model.domain.MenuCategoryEntity;
import ia.deliz.app.model.validation.NewEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

@Getter
@Setter
@AllArgsConstructor
public class MenuItemDTO {

  @NotNull(
      message = "The field 'name' cannot be null.",
      groups = {NewEntity.class})
  @Size(
      min = 1,
      max = 255,
      message = "The field 'name' cannot be empty or longer than 255 characters.",
      groups = {NewEntity.class, Default.class})
  private String name;

  @Size(
      max = 255,
      message = "The field 'description' cannot be longer than 1024 characters.",
      groups = {NewEntity.class, Default.class})
  private String description;

  @NotNull(
      message = "The field 'price' cannot be null.",
      groups = {NewEntity.class})
  @Positive(
      message = "The field 'price' cannot be negative.",
      groups = {NewEntity.class, Default.class})
  private Float price;

  @NotNull(
      message = "The field 'category' cannot be null.",
      groups = {NewEntity.class})
  private MenuCategoryEntity category;
}
