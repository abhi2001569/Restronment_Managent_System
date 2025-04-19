package com.RMS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class DingingTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tableId;
	private String tableNumber;
	private String tablCapacity;
	private String tableAvailable;
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}
	public String getTableNumber() {
		return tableNumber;
	}
	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}
	public String getTablCapacity() {
		return tablCapacity;
	}
	public void setTablCapacity(String tablCapacity) {
		this.tablCapacity = tablCapacity;
	}
	public String getTableAvailable() {
		return tableAvailable;
	}
	public void setTableAvailable(String tableAvailable) {
		this.tableAvailable = tableAvailable;
	}
	
	

}
