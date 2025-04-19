package com.RMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.RMS.Entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByCustomer_CustId(int custId);
}