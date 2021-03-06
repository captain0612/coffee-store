package com.csdemo.coffeestore.service;

import com.csdemo.coffeestore.constant.ErrorMessageConstants;
import com.csdemo.coffeestore.contract.CartResponse;
import com.csdemo.coffeestore.contract.OrderConfirmation;
import com.csdemo.coffeestore.dto.CartRequest;
import com.csdemo.coffeestore.entity.Cart;
import com.csdemo.coffeestore.entity.Items;
import com.csdemo.coffeestore.entity.Order;
import com.csdemo.coffeestore.repository.CartRepository;
import com.csdemo.coffeestore.repository.ItemsRepository;
import com.csdemo.coffeestore.repository.OrderRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

  @Autowired private ItemsRepository itemsRepo;
  @Autowired private CartRepository cartRepo;
  @Autowired private OrderRepository ordersRepo;

  public OrderConfirmation confirmOrder(int OrderID) {
    List<Cart> cartItems = cartRepo.findByCartId(OrderID);
    OrderConfirmation response = createOrderResponse(OrderID);
    for (Cart item : cartItems) {
      Items repoItem = itemsRepo.findByname(item.getName());
      if (repoItem != null) {
        int repoQuantity = repoItem.getQuantity();
        if (repoQuantity > 0) {
          if (repoQuantity < item.getQuantity()) {
            repoItem.setQuantity(0);
          } else {
            repoItem.setQuantity(repoItem.getQuantity() - item.getQuantity());
          }

          itemsRepo.save(repoItem);
        }
      }
    }
    return response;
  }

  public OrderConfirmation createOrderResponse(int OrderID) {
    List<Cart> cartItems = cartRepo.findByCartId(OrderID);
    OrderConfirmation confirmation = new OrderConfirmation();
    List<CartResponse> responceList = confirmation.getResponseList();
    CartResponse responseObj;
    int orderTotal = 0;

    for (Cart item : cartItems) {
      int totalPrice = 0;
      String Message = ErrorMessageConstants.ITEM_NOT_AVAILABLE;
      responseObj = new CartResponse();
      Items repoItem = itemsRepo.findByname(item.getName());

      if (repoItem != null) {
        BeanUtils.copyProperties(repoItem, responseObj);
        int cartQuantity = item.getQuantity();
        int Itemprice = responseObj.getPrice();
        int actual = cartQuantity;
        int repoQuantity = repoItem.getQuantity();
        if (repoQuantity > 0) {
          if (repoQuantity < cartQuantity) {
            actual = Math.abs(repoQuantity - cartQuantity);
          }
          Message = actual + "/" + cartQuantity + " Avaiable";
          totalPrice = actual * Itemprice;
          orderTotal += totalPrice;
          responseObj.setPricePerItem(Itemprice);
        }
      } else {
        responseObj.setName(item.getName());
      }
      responseObj.setPrice(totalPrice);
      responseObj.setAvailableQuantity(Message);
      responceList.add(responseObj);
    }
    confirmation.setOrderTotal(orderTotal);
    confirmation.setOrder_id(OrderID);
    return confirmation;
  }

  public OrderConfirmation createCart(List<CartRequest> cartRequests) {
    Order order = new Order();
    ordersRepo.save(order);
    for (CartRequest cartRequest : cartRequests) {
      Cart cart = new Cart(order);
      cart.setName(cartRequest.getName());
      cart.setQuantity(cartRequest.getQuantity());
      cartRepo.save(cart);
    }
    return createOrderResponse(order.getOrder_id());
  }
}
