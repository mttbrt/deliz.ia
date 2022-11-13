package ia.deliz.app.controller;

import ia.deliz.app.model.domain.MenuItemEntity;
import ia.deliz.app.model.dto.MenuItemDTO;
import ia.deliz.app.model.response.MenuItemAssembler;
import ia.deliz.app.model.response.MenuItemModel;
import ia.deliz.app.model.validation.NewEntity;
import ia.deliz.app.service.MenuItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "menu-items", produces = "application/json")
@Tag(name = "Menu Items")
public class MenuItemController {

  @Autowired private MenuItemService menuItemService;

  @GetMapping
  public CollectionModel<MenuItemModel> getMenuItems() {
    Iterable<MenuItemEntity> entities = menuItemService.getMenuItems();
    CollectionModel<MenuItemModel> menuItemModels =
        new MenuItemAssembler().toCollectionModel(entities);
    menuItemModels.add(
        linkTo(methodOn(MenuItemController.class).getMenuItems()).withRel("menu-items"));
    return menuItemModels;
  }

  @GetMapping(path = "/{id}")
  public MenuItemModel getMenuItemById(@PathVariable @NotNull @Size(min = 1) Long id) {
    MenuItemEntity entity = menuItemService.getMenuItemById(id);
    return entityToModel(entity);
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public MenuItemModel postMenuItem(@RequestBody @Validated(NewEntity.class) MenuItemDTO dto) {
    MenuItemEntity entity = menuItemService.createMenuItem(dto);
    return entityToModel(entity);
  }

  @PutMapping(path = "/{id}", consumes = "application/json")
  public MenuItemModel putMenuItem(
      @PathVariable @NotNull @Size(min = 1) Long id,
      @RequestBody @Validated(NewEntity.class) MenuItemDTO dto) {
    MenuItemEntity entity = menuItemService.replaceMenuItem(id, dto);
    return entityToModel(entity);
  }

  @PatchMapping(path = "/{id}", consumes = "application/json")
  public MenuItemModel patchMenuItem(
      @PathVariable @NotNull @Size(min = 1) Long id, @RequestBody @Valid MenuItemDTO dto) {
    MenuItemEntity entity = menuItemService.updateMenuItem(id, dto);
    return entityToModel(entity);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteMenuItem(@PathVariable @NotNull @Size(min = 1) Long id) {
    menuItemService.deleteMenuItem(id);
  }

  private MenuItemModel entityToModel(MenuItemEntity entity) {
    MenuItemModel model = new MenuItemAssembler().toModel(entity);
    return model.add(
        linkTo(methodOn(MenuItemController.class).getMenuItems()).withRel("menu-items"));
  }
}
