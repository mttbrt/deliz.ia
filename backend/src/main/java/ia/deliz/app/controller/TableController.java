package ia.deliz.app.controller;

import ia.deliz.app.domain.entity.Table;
import ia.deliz.app.domain.model.TableAssembler;
import ia.deliz.app.domain.model.TableModel;
import ia.deliz.app.service.TableService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "tables", produces = "application/json")
@Tag(name = "Tables")
public class TableController {

  // TODO: validate fields with validation groups, exception/error handling, testing

  @Autowired private TableService tableService;

  @GetMapping
  public CollectionModel<TableModel> getTables() {
    Iterable<Table> tables = tableService.getTables();
    CollectionModel<TableModel> tableResources = new TableAssembler().toCollectionModel(tables);
    tableResources.add(linkTo(methodOn(TableController.class).getTables()).withRel("tables"));
    return tableResources;
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public TableModel postTable(@RequestBody TableModel table) {
    Table savedTable = tableService.createTable(table);
    TableModel tableResource = new TableAssembler().toModel(savedTable);
    tableResource.add(linkTo(methodOn(TableController.class).getTables()).withRel("tables"));
    return tableResource;
  }

  @PutMapping(path = "/{tableId}", consumes = "application/json")
  public TableModel putTable(@PathVariable Long tableId, @RequestBody TableModel table) {
    Table savedTable = tableService.replaceTable(tableId, table);
    TableModel tableResource = new TableAssembler().toModel(savedTable);
    tableResource.add(linkTo(methodOn(TableController.class).getTables()).withRel("tables"));
    return tableResource;
  }

  @PatchMapping(path = "/{tableId}", consumes = "application/json")
  public TableModel patchTable(@PathVariable Long tableId, @RequestBody TableModel table) {
    Table savedTable = tableService.updateTable(tableId, table);
    TableModel tableResource = new TableAssembler().toModel(savedTable);
    tableResource.add(linkTo(methodOn(TableController.class).getTables()).withRel("tables"));
    return tableResource;
  }

  @DeleteMapping(path = "/{tableId}", consumes = "application/json")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTable(@PathVariable Long tableId) {
    tableService.deleteTable(tableId);
  }
}
