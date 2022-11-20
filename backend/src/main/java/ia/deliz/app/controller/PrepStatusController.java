package ia.deliz.app.controller;

import ia.deliz.app.model.domain.PrepStatusEntity;
import ia.deliz.app.model.response.PrepStatusAssembler;
import ia.deliz.app.model.response.PrepStatusModel;
import ia.deliz.app.service.PrepStatusService;
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
@RequestMapping(path = "statuses", produces = "application/json")
@Tag(name = "Statuses")
public class PrepStatusController {

  @Autowired private PrepStatusService prepStatusService;

  @GetMapping
  public CollectionModel<PrepStatusModel> getPrepStatuses() {
    Iterable<PrepStatusEntity> entities = prepStatusService.getPrepStatuses();
    CollectionModel<PrepStatusModel> prepStatusModels = new PrepStatusAssembler().toCollectionModel(entities);
    prepStatusModels.add(linkTo(methodOn(PrepStatusController.class).getPrepStatuses()).withRel("statuses"));
    return prepStatusModels;
  }

  @GetMapping(path = "/{id}")
  public PrepStatusModel getPrepStatusById(@PathVariable @NotNull @Size(min = 1) Long id) {
    PrepStatusEntity entity = prepStatusService.getPrepStatusById(id);
    return entityToModel(entity);
  }

  private PrepStatusModel entityToModel(PrepStatusEntity entity) {
    PrepStatusModel prepStatusModel = new PrepStatusAssembler().toModel(entity);
    return prepStatusModel.add(linkTo(methodOn(PrepStatusController.class).getPrepStatuses()).withRel("statuses"));
  }
}
