package ia.deliz.app.controller;

import ia.deliz.app.model.domain.TableEntity;
import ia.deliz.app.model.dto.TableDTO;
import ia.deliz.app.model.response.TableAssembler;
import ia.deliz.app.model.response.TableModel;
import ia.deliz.app.model.validation.NewEntity;
import ia.deliz.app.service.TableService;
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
@RequestMapping(path = "tables", produces = "application/json")
@Tag(name = "Tables")
public class TableController {

  @Autowired private TableService tableService;

  @GetMapping
  public CollectionModel<TableModel> getTables() {
    Iterable<TableEntity> entities = tableService.getTables();
    CollectionModel<TableModel> tableModels = new TableAssembler().toCollectionModel(entities);
    tableModels.add(linkTo(methodOn(TableController.class).getTables()).withRel("tables"));
    return tableModels;
  }

  @GetMapping(path = "/{id}")
  public TableModel getTableById(@PathVariable @NotNull @Size(min = 1) Long id) {
    TableEntity entity = tableService.getTableById(id);
    return entityToModel(entity);
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public TableModel postTable(@RequestBody @Validated(NewEntity.class) TableDTO dto) {
    TableEntity entity = tableService.createTable(dto);
    return entityToModel(entity);
  }

  @PutMapping(path = "/{id}", consumes = "application/json")
  public TableModel putTable(
      @PathVariable @NotNull @Size(min = 1) Long id,
      @RequestBody @Validated(NewEntity.class) TableDTO dto) {
    TableEntity entity = tableService.replaceTable(id, dto);
    return entityToModel(entity);
  }

  @PatchMapping(path = "/{id}", consumes = "application/json")
  public TableModel patchTable(
      @PathVariable @NotNull @Size(min = 1) Long id, @RequestBody @Valid TableDTO dto) {
    TableEntity entity = tableService.updateTable(id, dto);
    return entityToModel(entity);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTable(@PathVariable @NotNull @Size(min = 1) Long id) {
    tableService.deleteTable(id);
  }

  private TableModel entityToModel(TableEntity entity) {
    TableModel tableModel = new TableAssembler().toModel(entity);
    return tableModel.add(linkTo(methodOn(TableController.class).getTables()).withRel("tables"));
  }
}
