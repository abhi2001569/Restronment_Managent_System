package com.RMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.RMS.Entity.Customers;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customers, Integer> {
    Optional<Customers> findByGmailId(String gmailId);
}