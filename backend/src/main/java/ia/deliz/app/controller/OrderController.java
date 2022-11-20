package ia.deliz.app.controller;

import ia.deliz.app.model.domain.OrderEntity;
import ia.deliz.app.model.dto.OrderDTO;
import ia.deliz.app.model.response.OrderAssembler;
import ia.deliz.app.model.response.OrderModel;
import ia.deliz.app.model.validation.NewEntity;
import ia.deliz.app.service.OrderService;
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
@RequestMapping(path = "orders", produces = "application/json")
@Tag(name = "Orders")
public class OrderController {

  @Autowired private OrderService orderService;

  @GetMapping
  public CollectionModel<OrderModel> getOrders() {
    Iterable<OrderEntity> entities = orderService.getOrders();
    CollectionModel<OrderModel> orderModels = new OrderAssembler().toCollectionModel(entities);
    orderModels.add(linkTo(methodOn(OrderController.class).getOrders()).withRel("orders"));
    return orderModels;
  }

  @GetMapping(path = "/{id}")
  public OrderModel getOrderById(@PathVariable @NotNull @Size(min = 1) Long id) {
    OrderEntity entity = orderService.getOrderById(id);
    return entityToModel(entity);
  }

  @PostMapping(consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public OrderModel postOrder(@RequestBody @Validated(NewEntity.class) OrderDTO dto) {
    OrderEntity entity = orderService.createOrder(dto);
    return entityToModel(entity);
  }

  @PutMapping(path = "/{id}", consumes = "application/json")
  public OrderModel putOrder(
      @PathVariable @NotNull @Size(min = 1) Long id,
      @RequestBody @Validated(NewEntity.class) OrderDTO dto) {
    OrderEntity entity = orderService.replaceOrder(id, dto);
    return entityToModel(entity);
  }

  @PatchMapping(path = "/{id}", consumes = "application/json")
  public OrderModel patchOrder(
      @PathVariable @NotNull @Size(min = 1) Long id, @RequestBody @Valid OrderDTO dto) {
    OrderEntity entity = orderService.updateOrder(id, dto);
    return entityToModel(entity);
  }

  @DeleteMapping(path = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrder(@PathVariable @NotNull @Size(min = 1) Long id) {
    orderService.deleteOrder(id);
  }

  private OrderModel entityToModel(OrderEntity entity) {
    OrderModel model = new OrderAssembler().toModel(entity);
    return model.add(linkTo(methodOn(OrderController.class).getOrders()).withRel("orders"));
  }
}
