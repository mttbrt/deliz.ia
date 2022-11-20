package ia.deliz.app.controller;

import ia.deliz.app.model.domain.OrderMenuItemEntity;
import ia.deliz.app.model.dto.OrderMenuItemDTO;
import ia.deliz.app.model.response.OrderMenuItemAssembler;
import ia.deliz.app.model.response.OrderMenuItemModel;
import ia.deliz.app.model.validation.NewEntity;
import ia.deliz.app.service.OrderMenuItemService;
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
@RequestMapping(path = "order-menu-items", produces = "application/json")
@Tag(name = "Order Menu Items")
public class OrderMenuItemController {

  @Autowired private OrderMenuItemService orderMenuItemService;

  @GetMapping
  public CollectionModel<OrderMenuItemModel> getOrderMenuItems() {
    Iterable<OrderMenuItemEntity> entities = orderMenuItemService.getOrderMenuItems();
    CollectionModel<OrderMenuItemModel> orderMenuItemModels = new OrderMenuItemAssembler().toCollectionModel(entities);
    orderMenuItemModels.add(linkTo(methodOn(OrderMenuItemController.class).getOrderMenuItems()).withRel("order-menu-items"));
    return orderMenuItemModels;
  }

  @GetMapping(path = "/{id}")
  public OrderMenuItemModel getOrderMenuItemById(@PathVariable @NotNull @Size(min = 1) Long id) {
    OrderMenuItemEntity entity = orderMenuItemService.getOrderMenuItemById(id);
    return entityToModel(entity);
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public OrderMenuItemModel postOrderMenuItem(@RequestBody @Validated(NewEntity.class) OrderMenuItemDTO dto) {
    OrderMenuItemEntity entity = orderMenuItemService.createOrderMenuItem(dto);
    return entityToModel(entity);
  }

  @PutMapping(path = "/{id}", consumes = "application/json")
  public OrderMenuItemModel putOrderMenuItem(
      @PathVariable @NotNull @Size(min = 1) Long id,
      @RequestBody @Validated(NewEntity.class) OrderMenuItemDTO dto) {
    OrderMenuItemEntity entity = orderMenuItemService.replaceOrderMenuItem(id, dto);
    return entityToModel(entity);
  }

  @PatchMapping(path = "/{id}", consumes = "application/json")
  public OrderMenuItemModel patchOrderMenuItem(
      @PathVariable @NotNull @Size(min = 1) Long id, @RequestBody @Valid OrderMenuItemDTO dto) {
    OrderMenuItemEntity entity = orderMenuItemService.updateOrderMenuItem(id, dto);
    return entityToModel(entity);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrderMenuItem(@PathVariable @NotNull @Size(min = 1) Long id) {
    orderMenuItemService.deleteOrderMenuItem(id);
  }

  private OrderMenuItemModel entityToModel(OrderMenuItemEntity entity) {
    OrderMenuItemModel model = new OrderMenuItemAssembler().toModel(entity);
    return model.add(linkTo(methodOn(OrderMenuItemController.class).getOrderMenuItems()).withRel("order-menu-items"));
  }
}
