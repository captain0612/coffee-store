package com.csdemo.coffeestore.service;

import com.csdemo.coffeestore.dto.ItemsRequest;
import com.csdemo.coffeestore.entity.Items;
import com.csdemo.coffeestore.repository.ItemsRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {

  @Autowired private ItemsRepository itemsRepo;

  public void getItemsList() {
    itemsRepo.findAllItems();
  }

  public void addItems(List<ItemsRequest> itemRequests) {
    for (ItemsRequest itemRequest : itemRequests) {
      Items item = new Items();
      BeanUtils.copyProperties(itemRequest, item);
      Items repoItem = itemsRepo.findByname(item.getName());
      if (repoItem != null) {
        repoItem.setQuantity(repoItem.getQuantity() + item.getQuantity());
        repoItem.setPrice(item.getPrice());
        itemsRepo.save(repoItem);
      } else {
        itemsRepo.save(item);
      }
    }
  }

  public void deleteItemByName(String itemName) {
    Items repoItem = itemsRepo.findByname(itemName);
    itemsRepo.delete(repoItem);
  }
}
