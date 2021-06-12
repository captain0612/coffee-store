package com.csdemo.coffeestore.service;

import com.csdemo.coffeestore.contract.OrderResponse;
import com.csdemo.coffeestore.dto.OrderRequest;
import com.csdemo.coffeestore.entity.Items;
import com.csdemo.coffeestore.repository.ItemsRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class OrderService {
    @Autowired
    private ItemsRepository itemsRepo;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Items repoItem = itemsRepo.findByname(orderRequest.getName());

        if (repoItem != null) {
            OrderResponse responseObj = new OrderResponse();
            BeanUtils.copyProperties(repoItem, responseObj);
            String Message = "item not available";
            int orderQuantity = orderRequest.getQuantity();
            int Iteamprice = responseObj.getPrice();
            int totalPrice =0;
            int actual = orderQuantity;
            int repoQuantity = repoItem.getQuantity();
            if (repoQuantity > 0) {
                if (repoQuantity < orderQuantity) {
                    actual = Math.abs(repoQuantity - orderQuantity);

                }
                Message = actual + "/" + orderQuantity + " Avaiable";
                totalPrice = actual*Iteamprice;
            }

            responseObj.setPrice(totalPrice);
            responseObj.setAvailability(Message);
            return responseObj;

        }

        return null;
    }
}
