package com.RMS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RMS.Entity.Customers;
import com.RMS.Entity.ReservationTableEntity;

public interface ReservationRepository extends JpaRepository<ReservationTableEntity, Integer> {
    List<ReservationTableEntity> findByCustomer(Customers customer);
}