package com.RMS.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.RMS.Entity.FoodCategoryEntity;
import com.RMS.Entity.Foods;
import com.RMS.Repository.FoodCategoryRepository;
import com.RMS.Repository.FoodsRepository;

import java.io.IOException;
import java.util.List;

@Controller
public class FoodsController {

    @Autowired
    private FoodsRepository foodrepo;

    @Autowired
    private FoodCategoryRepository foodCategoryRepo;

    @GetMapping("/gofoodform")
    public String gofoodadd(Model model) {
        List<FoodCategoryEntity> categories = foodCategoryRepo.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("foodadd", new Foods());
        return "AddFood";
    }

    @PostMapping("/savefoods")
    public String savefoods(@ModelAttribute Foods foodadd, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (!imageFile.isEmpty()) {
            foodadd.setImage(imageFile.getBytes());
        }
        foodrepo.save(foodadd);
        return "redirect:/gofoodform";
    }

    @RequestMapping("/displayfood")
    public String displayfood(Model model) {
        List<Foods> lidtfood = foodrepo.findAll();
        model.addAttribute("lidtfood", lidtfood);
        return "DisplayFoods";
    }
    
    @GetMapping("/deletefood/{foodId}")
    public String deletefoodcate(@PathVariable int foodId) {

    	foodrepo.deleteById(foodId);

        return "redirect:/displayfood";

    }
}