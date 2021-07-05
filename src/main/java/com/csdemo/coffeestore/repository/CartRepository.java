package com.csdemo.coffeestore.repository;

import com.csdemo.coffeestore.contract.SoldItemResponse;
import com.csdemo.coffeestore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
  @Query("FROM Cart cart WHERE order_id = ?1")
  List<Cart> findByCartId(int cart_id);

  @Query(
      value =
          "select new com.csdemo.coffeestore.contract.SoldItemResponse(cart.name,sum(cart.quantity)) "
              + "from Cart as cart where cart.createDateTime>=:date GROUP BY cart.name ")
  List<SoldItemResponse> findByCreatedAtBefore(@Param("date") Date date);
}
