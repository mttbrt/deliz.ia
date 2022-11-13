package ia.deliz.app.controller;

import ia.deliz.app.model.domain.MenuCategoryEntity;
import ia.deliz.app.model.dto.MenuCategoryDTO;
import ia.deliz.app.model.response.MenuCategoryAssembler;
import ia.deliz.app.model.response.MenuCategoryModel;
import ia.deliz.app.model.validation.NewEntity;
import ia.deliz.app.service.MenuCategoryService;
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
@RequestMapping(path = "menu-categories", produces = "application/json")
@Tag(name = "Menu Categories")
public class MenuCategoryController {

  @Autowired private MenuCategoryService menuCategoryService;

  @GetMapping
  public CollectionModel<MenuCategoryModel> getMenuCategories() {
    Iterable<MenuCategoryEntity> entities = menuCategoryService.getMenuCategories();
    CollectionModel<MenuCategoryModel> menuCategoryModels =
        new MenuCategoryAssembler().toCollectionModel(entities);
    menuCategoryModels.add(
        linkTo(methodOn(MenuCategoryController.class).getMenuCategories())
            .withRel("menu-categories"));
    return menuCategoryModels;
  }

  @GetMapping(path = "/{id}")
  public MenuCategoryModel getMenuCategoryById(@PathVariable @NotNull @Size(min = 1) Long id) {
    MenuCategoryEntity entity = menuCategoryService.getMenuCategoryById(id);
    return entityToModel(entity);
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public MenuCategoryModel postMenuCategory(
      @RequestBody @Validated(NewEntity.class) MenuCategoryDTO dto) {
    MenuCategoryEntity entity = menuCategoryService.createMenuCategory(dto);
    return entityToModel(entity);
  }

  @PutMapping(path = "/{id}", consumes = "application/json")
  public MenuCategoryModel putMenuCategory(
      @PathVariable @NotNull @Size(min = 1) Long id,
      @RequestBody @Validated(NewEntity.class) MenuCategoryDTO dto) {
    MenuCategoryEntity entity = menuCategoryService.replaceMenuCategory(id, dto);
    return entityToModel(entity);
  }

  @PatchMapping(path = "/{id}", consumes = "application/json")
  public MenuCategoryModel patchMenuCategory(
      @PathVariable @NotNull @Size(min = 1) Long id, @RequestBody @Valid MenuCategoryDTO dto) {
    MenuCategoryEntity entity = menuCategoryService.updateMenuCategory(id, dto);
    return entityToModel(entity);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteMenuCategory(@PathVariable @NotNull @Size(min = 1) Long id) {
    menuCategoryService.deleteMenuCategory(id);
  }

  private MenuCategoryModel entityToModel(MenuCategoryEntity entity) {
    MenuCategoryModel model = new MenuCategoryAssembler().toModel(entity);
    return model.add(
        linkTo(methodOn(MenuCategoryController.class).getMenuCategories())
            .withRel("menu-categories"));
  }
}
