package com.RMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.RMS.Entity.Payment;
import com.RMS.Entity.Cart;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    Payment findByCart(Cart cart);
}