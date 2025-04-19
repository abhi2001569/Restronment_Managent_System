package com.RMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.RMS.Entity.Cart;
import com.RMS.Entity.CartItem;
import com.RMS.Entity.Customers;
import com.RMS.Entity.Foods;
import com.RMS.Repository.CartRepository;
import com.RMS.Repository.CartItemRepository;
import com.RMS.Repository.FoodsRepository;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private FoodsRepository foodRepo;

    @PostMapping("/addtocart")
    public String addToCart(@RequestParam("foodId") int foodId, HttpSession session) {
        Customers customer = (Customers) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/logincustomer";
        }

        Cart cart = cartRepo.findByCustomer_CustId(customer.getCustId());
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            cartRepo.save(cart);
        }

        Foods food = foodRepo.findById(foodId).orElse(null);
        if (food != null) {
            CartItem cartItem = new CartItem();
            cartItem.setFood(food);
            cartItem.setCart(cart);
            cartItem.setQuantity(1); // Default quantity
            cartItemRepo.save(cartItem);
        }

        return "redirect:/viewCart";
    }

    @GetMapping("/viewCart")
    public String viewCart(Model model, HttpSession session) {
        Customers customer = (Customers) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/logincustomer";
        }

        Cart cart = cartRepo.findByCustomer_CustId(customer.getCustId());
        if (cart != null) {
            List<CartItem> cartItems = cart.getCartItems();
            model.addAttribute("cartItems", cartItems);

            // Calculate total amount
            double totalAmount = 0;
            for (CartItem item : cartItems) {
                totalAmount += Double.parseDouble(item.getFood().getFoodprice()) * item.getQuantity();
            }
            model.addAttribute("totalAmount", totalAmount);
        } else {
            model.addAttribute("cartItems", new ArrayList<>()); // Add an empty list if cart is null
            model.addAttribute("totalAmount", 0.0); // Set total amount to 0 if cart is null
        }

        return "Cart";
    }

    @GetMapping("/removeFromCart/{cartItemId}")
    public String removeFromCart(@PathVariable int cartItemId) {
        cartItemRepo.deleteById(cartItemId);
        return "redirect:/viewCart";
    }

    @GetMapping("/generateInvoice")
    public String generateInvoice(Model model, HttpSession session) {
        Customers customer = (Customers) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/logincustomer";
        }

        Cart cart = cartRepo.findByCustomer_CustId(customer.getCustId());
        if (cart != null) {
            List<CartItem> cartItems = cart.getCartItems();
            model.addAttribute("customer", customer);
            model.addAttribute("cartItems", cartItems);

            // Calculate total amount again (to be sure)
            double totalAmount = 0;
            for (CartItem item : cartItems) {
                totalAmount += Double.parseDouble(item.getFood().getFoodprice()) * item.getQuantity();
            }
            model.addAttribute("totalAmount", totalAmount);
            model.addAttribute("paymentStatus", "Paid"); // Set the payment status
        } else {
            model.addAttribute("errorMessage", "No items in the cart to generate an invoice.");
            return "redirect:/viewCart"; // Or another appropriate page
        }

        return "Invoice"; // Name of the new JSP page
    }
}