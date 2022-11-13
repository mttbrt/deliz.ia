package ia.deliz.app.controller;

import ia.deliz.app.model.domain.UserEntity;
import ia.deliz.app.model.dto.UserDTO;
import ia.deliz.app.model.response.UserAssembler;
import ia.deliz.app.model.response.UserModel;
import ia.deliz.app.model.validation.NewEntity;
import ia.deliz.app.service.UserService;
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
@RequestMapping(path = "users", produces = "application/json")
@Tag(name = "Users")
public class UserController {

  @Autowired private UserService userService;

  @GetMapping
  public CollectionModel<UserModel> getUsers() {
    Iterable<UserEntity> entities = userService.getUsers();
    CollectionModel<UserModel> userModels = new UserAssembler().toCollectionModel(entities);
    userModels.add(linkTo(methodOn(UserController.class).getUsers()).withRel("users"));
    return userModels;
  }

  @GetMapping(path = "/{id}")
  public UserModel getUserById(@PathVariable @NotNull @Size(min = 1) Long id) {
    UserEntity entity = userService.getUserById(id);
    return entityToModel(entity);
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public UserModel postUser(@RequestBody @Validated(NewEntity.class) UserDTO dto) {
    UserEntity entity = userService.createUser(dto);
    return entityToModel(entity);
  }

  @PutMapping(path = "/{id}", consumes = "application/json")
  public UserModel putUser(
      @PathVariable @NotNull @Size(min = 1) Long id,
      @RequestBody @Validated(NewEntity.class) UserDTO dto) {
    UserEntity entity = userService.replaceUser(id, dto);
    return entityToModel(entity);
  }

  @PatchMapping(path = "/{id}", consumes = "application/json")
  public UserModel patchUser(
      @PathVariable @NotNull @Size(min = 1) Long id, @RequestBody @Valid UserDTO dto) {
    UserEntity entity = userService.updateUser(id, dto);
    return entityToModel(entity);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable @NotNull @Size(min = 1) Long id) {
    userService.deleteUser(id);
  }

  private UserModel entityToModel(UserEntity entity) {
    UserModel model = new UserAssembler().toModel(entity);
    return model.add(linkTo(methodOn(UserController.class).getUsers()).withRel("users"));
  }
}
