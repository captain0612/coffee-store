package com.csdemo.coffeestore.service;

import com.csdemo.coffeestore.contract.OrderConfirmation;
import com.csdemo.coffeestore.contract.OrderResponse;
import com.csdemo.coffeestore.dto.OrderRequest;
import com.csdemo.coffeestore.entity.Items;
import com.csdemo.coffeestore.repository.ItemsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class OrderService {
    List<OrderRequest> orderRequests = new ArrayList<>();
    @Autowired
    private ItemsRepository itemsRepo;

    public List<OrderResponse> placeOrder(List<OrderRequest> orderRequests) {
        this.orderRequests = orderRequests;
        List<OrderResponse> ResponceList = new ArrayList<>();
        OrderResponse responseObj;
        int totalPrice = 0;
        String Message = "item not available";

        for (OrderRequest orderRequest : orderRequests) {
            responseObj = new OrderResponse();
            Items repoItem = itemsRepo.findByname(orderRequest.getName());

            if (repoItem != null) {
                BeanUtils.copyProperties(repoItem, responseObj);
                int orderQuantity = orderRequest.getQuantity();
                int Iteamprice = responseObj.getPrice();

                int actual = orderQuantity;
                int repoQuantity = repoItem.getQuantity();
                if (repoQuantity > 0) {
                    if (repoQuantity < orderQuantity) {
                        actual = Math.abs(repoQuantity - orderQuantity);

                    }
                    Message = actual + "/" + orderQuantity + " Avaiable";
                    totalPrice = actual * Iteamprice;
                }
            } else {
                Message = "Item Not available in the Menu";
                responseObj.setName(orderRequest.getName());
            }

            responseObj.setPrice(totalPrice);
            responseObj.setAvailability(Message);
            ResponceList.add(responseObj);

        }

        return ResponceList;
    }


    public OrderConfirmation confirmOrder() {
        OrderConfirmation obj = new OrderConfirmation();
        List<OrderResponse> responceList = obj.getResponceList();
        OrderResponse responseObj;
        int orderTotal = 0;


        for (OrderRequest orderRequest : orderRequests) {
            int totalPrice = 0;
            String Message = "item not available";
            responseObj = new OrderResponse();
            Items repoItem = itemsRepo.findByname(orderRequest.getName());

            if (repoItem != null) {
                BeanUtils.copyProperties(repoItem, responseObj);
                int orderQuantity = orderRequest.getQuantity();
                int Iteamprice = responseObj.getPrice();

                int actual = orderQuantity;
                int repoQuantity = repoItem.getQuantity();
                if (repoQuantity > 0) {
                    if (repoQuantity < orderQuantity) {
                        actual = Math.abs(repoQuantity - orderQuantity);
                        repoItem.setQuantity(0);
                    } else {
                        repoItem.setQuantity(repoQuantity - orderQuantity);
                    }

                    itemsRepo.save(repoItem);
                    Message = actual + "/" + orderQuantity + " Avaiable";
                    totalPrice = actual * Iteamprice;
                    orderTotal += totalPrice;
                }
            } else {
                Message = "Item Not available in the Menu";
                responseObj.setName(orderRequest.getName());
            }

            responseObj.setPrice(totalPrice);
            responseObj.setAvailability(Message);
            responceList.add(responseObj);

        }
        obj.setTotal(orderTotal);
        clearOrder();
        return obj;
    }

    public void clearOrder() {
        orderRequests.clear();
    }
}

