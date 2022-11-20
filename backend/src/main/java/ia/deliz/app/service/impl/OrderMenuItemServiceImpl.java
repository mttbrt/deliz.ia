package ia.deliz.app.service.impl;

import ia.deliz.app.model.domain.OrderMenuItemEntity;
import ia.deliz.app.model.dto.OrderMenuItemDTO;
import ia.deliz.app.service.OrderMenuItemService;
import org.springframework.stereotype.Service;

@Service
public class OrderMenuItemServiceImpl implements OrderMenuItemService {

  @Override
  public Iterable<OrderMenuItemEntity> getOrderMenuItems() {
    return null;
  }

  @Override
  public OrderMenuItemEntity getOrderMenuItemById(Long id) {
    return null;
  }

  @Override
  public OrderMenuItemEntity createOrderMenuItem(OrderMenuItemDTO dto) { // TODO; this should check if an order already exists or not
    return null;
  }

  @Override
  public OrderMenuItemEntity replaceOrderMenuItem(Long id, OrderMenuItemDTO dto) {
    return null;
  }

  @Override
  public OrderMenuItemEntity updateOrderMenuItem(Long id, OrderMenuItemDTO dto) {
    return null;
  }

  @Override
  public void deleteOrderMenuItem(Long id) {
  }
}
