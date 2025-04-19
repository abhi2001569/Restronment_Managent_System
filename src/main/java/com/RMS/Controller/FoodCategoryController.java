package com.RMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.RMS.Entity.FoodCategoryEntity;
import com.RMS.Repository.FoodCategoryRepository;

@Controller
public class FoodCategoryController {

	@Autowired
	private FoodCategoryRepository foodcateRepo;
	
	@RequestMapping("/FoodCateform")
	public String FoodCategoryForm() {
		
		return "AddFoodCategory";
	}
	
	@PostMapping("/saveFoodCategory")
	public String saveFoodCate(@ModelAttribute FoodCategoryEntity fodcate, Model model) {
	    
	    foodcateRepo.save(fodcate);
	    
	    model.addAttribute("successmessage", "Food Category Added Successfully");
	    
	    return "AddFoodCategory";  // Directly return the view to retain the message
	}
	
	@RequestMapping("/aisplayfoodcategory")
	public String DisplayFoodCategpry(Model model) {
		
		List<FoodCategoryEntity> list = foodcateRepo.findAll();
		
		model.addAttribute("foodCate_data",list);
		
		return "DisplayFoodCategory";
	}
	
	@RequestMapping("/edit/{cate_id}")
	public String EditFoodCategory(@PathVariable int cate_id, Model model) {
		
		FoodCategoryEntity foodCate = foodcateRepo.findById(cate_id).orElse(null);
		
		model.addAttribute("foodCatedata",foodCate);
		
		return "EditFoodCategory";
		
		
	}
	
	@PostMapping("/update/{cate_id}")
	public String UpdateFoodCatefory(@PathVariable int cate_id, @ModelAttribute FoodCategoryEntity updatefoodCate, Model model) {
		
		FoodCategoryEntity foodCate = foodcateRepo.findById(cate_id).orElse(null);
		
		if(foodCate != null)
		{
			foodCate.setCategoryName(updatefoodCate.getCategoryName());	
			
			 model.addAttribute("successmessage", "Food Category Updated Successfully");
			
			foodcateRepo.save(foodCate);
		}
		
		return "redirect:/aisplayfoodcategory";
		
	}
	
	@GetMapping("/DeletefoodCate/{cate_id}")	
	public String deletefoodcate(@PathVariable int cate_id) {
		
		 foodcateRepo.deleteById(cate_id);
		
		return "redirect:/aisplayfoodcategory";
		
	}

	
	
}
