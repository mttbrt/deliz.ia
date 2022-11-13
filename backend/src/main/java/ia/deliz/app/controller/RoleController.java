package ia.deliz.app.controller;

import ia.deliz.app.model.domain.RoleEntity;
import ia.deliz.app.model.response.RoleAssembler;
import ia.deliz.app.model.response.RoleModel;
import ia.deliz.app.service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "roles", produces = "application/json")
@Tag(name = "Roles")
public class RoleController {

  @Autowired private RoleService roleService;

  @GetMapping
  public CollectionModel<RoleModel> getRoles() {
    Iterable<RoleEntity> entities = roleService.getRoles();
    CollectionModel<RoleModel> roleModels = new RoleAssembler().toCollectionModel(entities);
    roleModels.add(linkTo(methodOn(RoleController.class).getRoles()).withRel("roles"));
    return roleModels;
  }

  @GetMapping(path = "/{id}")
  public RoleModel getRoleById(@PathVariable @NotNull @Size(min = 1) Long id) {
    RoleEntity entity = roleService.getRoleById(id);
    return entityToModel(entity);
  }

  private RoleModel entityToModel(RoleEntity entity) {
    RoleModel roleModel = new RoleAssembler().toModel(entity);
    return roleModel.add(linkTo(methodOn(RoleController.class).getRoles()).withRel("roles"));
  }
}
