package com.RMS.Entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Foods {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int foodId;
	
	@Lob
    private byte[] image;
	private String foodName;
	private String foodDesc;
	private String foodprice;
	
	@ManyToOne
    @JoinColumn(name = "cateId") // This is the foreign key column in the Foods table
    private FoodCategoryEntity foodCategory;
	
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getFoodDesc() {
		return foodDesc;
	}
	public void setFoodDesc(String foodDesc) {
		this.foodDesc = foodDesc;
	}
	public String getFoodprice() {
		return foodprice;
	}
	public void setFoodprice(String foodprice) {
		this.foodprice = foodprice;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public FoodCategoryEntity getFoodCategory() {
		return foodCategory;
	}
	public void setFoodCategory(FoodCategoryEntity foodCategory) {
		this.foodCategory = foodCategory;
	}
	
	
	
	
	

}
