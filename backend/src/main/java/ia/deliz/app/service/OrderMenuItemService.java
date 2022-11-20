package ia.deliz.app.service;

import ia.deliz.app.model.domain.OrderMenuItemEntity;
import ia.deliz.app.model.dto.OrderMenuItemDTO;

public interface OrderMenuItemService {

  Iterable<OrderMenuItemEntity> getOrderMenuItems();

  OrderMenuItemEntity getOrderMenuItemById(Long id);

  OrderMenuItemEntity createOrderMenuItem(OrderMenuItemDTO dto);

  OrderMenuItemEntity replaceOrderMenuItem(Long id, OrderMenuItemDTO dto);

  OrderMenuItemEntity updateOrderMenuItem(Long id, OrderMenuItemDTO dto);

  void deleteOrderMenuItem(Long id);
}
