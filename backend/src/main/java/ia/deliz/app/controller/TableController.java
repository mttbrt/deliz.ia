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
    Iterable<TableEntity> tables = tableService.getTables();
    CollectionModel<TableModel> tableResources = new TableAssembler().toCollectionModel(tables);
    tableResources.add(linkTo(methodOn(TableController.class).getTables()).withRel("tables"));
    return tableResources;
  }

  @GetMapping(path = "/{tableId}")
  public TableModel getTableById(@PathVariable @NotNull @Size(min = 1) Long tableId) {
    TableEntity table = tableService.getTableById(tableId);
    return entityToModel(table);
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public TableModel postTable(@RequestBody @Validated(NewEntity.class) TableDTO table) {
    TableEntity savedTable = tableService.createTable(table);
    return entityToModel(savedTable);
  }

  @PutMapping(path = "/{tableId}", consumes = "application/json")
  public TableModel putTable(
      @PathVariable @NotNull @Size(min = 1) Long tableId,
      @RequestBody @Validated(NewEntity.class) TableDTO table) {
    TableEntity savedTable = tableService.replaceTable(tableId, table);
    return entityToModel(savedTable);
  }

  @PatchMapping(path = "/{tableId}", consumes = "application/json")
  public TableModel patchTable(
      @PathVariable @NotNull @Size(min = 1) Long tableId, @RequestBody @Valid TableDTO table) {
    TableEntity updatedTable = tableService.updateTable(tableId, table);
    return entityToModel(updatedTable);
  }

  @DeleteMapping(path = "/{tableId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTable(@PathVariable @NotNull @Size(min = 1) Long tableId) {
    tableService.deleteTable(tableId);
  }

  private TableModel entityToModel(TableEntity entity) {
    TableModel tableResource = new TableAssembler().toModel(entity);
    return tableResource.add(linkTo(methodOn(TableController.class).getTables()).withRel("tables"));
  }
}
