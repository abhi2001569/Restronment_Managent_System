package com.RMS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ReservationTableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reservationId;

    @ManyToOne
    @JoinColumn(name = "customer_id") // Foreign key column name
    private Customers customer;

    @ManyToOne
    @JoinColumn(name = "dining_table_id")
    private DingingTable diningTable;

    private String reservationTime; // You might want to use a Date/Time type
    private int numberOfGuests;

    // Existing getters and setters

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public DingingTable getDiningTable() {
		return diningTable;
	}

	public void setDiningTable(DingingTable diningTable) {
		this.diningTable = diningTable;
	}



	public String getReservationTime() {
		return reservationTime;
	}

	public void setReservationTime(String reservationTime) {
		this.reservationTime = reservationTime;
	}

	public int getNumberOfGuests() {
		return numberOfGuests;
	}

	public void setNumberOfGuests(int numberOfGuests) {
		this.numberOfGuests = numberOfGuests;
	}
    
    

    // ... (rest of your getters and setters)
}