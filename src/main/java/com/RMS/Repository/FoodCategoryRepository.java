package com.RMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RMS.Entity.FoodCategoryEntity;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategoryEntity, Integer> {
	
	

}
