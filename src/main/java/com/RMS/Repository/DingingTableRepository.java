package com.RMS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RMS.Entity.DingingTable;

@Repository
public interface DingingTableRepository extends JpaRepository<DingingTable, Integer> {

	boolean existsByTableNumber(String tableNumber);
	
	List<DingingTable> findByTableAvailable(String tableAvailable);
}