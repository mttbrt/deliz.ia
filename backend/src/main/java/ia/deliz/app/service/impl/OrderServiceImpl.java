package ia.deliz.app.service.impl;

import ia.deliz.app.exception.EntityNotFoundException;
import ia.deliz.app.model.domain.RoleEntity;
import ia.deliz.app.model.domain.OrderEntity;
import ia.deliz.app.model.dto.OrderDTO;
import ia.deliz.app.repository.RoleRepository;
import ia.deliz.app.repository.OrderRepository;
import ia.deliz.app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

  @Override
  public Iterable<OrderEntity> getOrders() {
    return null;
  }

  @Override
  public OrderEntity getOrderById(Long id) {
    return null;
  }

  @Override
  public OrderEntity createOrder(OrderDTO dto) { // TODO; this should check if an order already exists or not
    return null;
  }

  @Override
  public OrderEntity replaceOrder(Long id, OrderDTO dto) {
    return null;
  }

  @Override
  public OrderEntity updateOrder(Long id, OrderDTO dto) {
    return null;
  }

  @Override
  public void deleteOrder(Long id) {
  }
}
