package com.csdemo.coffeestore.service;

import com.csdemo.coffeestore.contract.SoldItemResponse;
import com.csdemo.coffeestore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class StatisticsService {

  @Autowired private CartRepository cartRepo;

  public List<SoldItemResponse> fetchitemCount(int noOfDays) {

    Calendar cal = Calendar.getInstance();
    Date today = cal.getTime();
    cal.add(Calendar.DAY_OF_MONTH, (-noOfDays));
    Date daysAgo = cal.getTime();
    return cartRepo.findByCreatedAtBefore(daysAgo);
  }
}
