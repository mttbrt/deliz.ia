package ia.deliz.app.service;

import ia.deliz.app.model.domain.OrderEntity;
import ia.deliz.app.model.dto.OrderDTO;

public interface OrderService {

  Iterable<OrderEntity> getOrders();

  OrderEntity getOrderById(Long id);

  OrderEntity createOrder(OrderDTO dto);

  OrderEntity replaceOrder(Long id, OrderDTO dto);

  OrderEntity updateOrder(Long id, OrderDTO dto);

  void deleteOrder(Long id);
}
