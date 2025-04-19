package com.RMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RMS.Entity.Foods;

public interface FoodsRepository extends JpaRepository<Foods, Integer> {

}
