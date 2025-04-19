package com.RMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.RMS.Entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}