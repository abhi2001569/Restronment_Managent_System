package com.RMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.RMS.Entity.Cart;
import com.RMS.Entity.Customers;
import com.RMS.Entity.Payment;
import com.RMS.Repository.CartRepository;
import com.RMS.Repository.PaymentRepository;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class CheckoutController {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    @GetMapping("/checkout")
    public String checkout(Model model, HttpSession session) {
        Customers customer = (Customers) session.getAttribute("customer");
        if (customer == null) {
            return "redirect:/logincustomer";
        }

        Cart cart = cartRepo.findByCustomer_CustId(customer.getCustId());
        if (cart != null) {
            System.out.println("Cart found for customer: " + customer.getCustId()); // Add this log
            Payment existingPayment = paymentRepo.findByCart(cart);

            if (existingPayment == null) {
                double totalAmount = cart.getCartItems().stream()
                    .mapToDouble(item -> Double.parseDouble(item.getFood().getFoodprice()) * item.getQuantity())
                    .sum();
                System.out.println("Total amount calculated: " + totalAmount); // Add this log

                Payment payment = new Payment();
                payment.setCart(cart);
                payment.setTotalAmount(totalAmount);
                payment.setPaymentDate(new Date());
                payment.setPaymentStatus("Paid");
                System.out.println("Payment object created: " + payment); // Add this log
                try {
                    paymentRepo.save(payment);
                    System.out.println("Payment saved successfully: " + payment.getPaymentId()); // Add this log
                    model.addAttribute("payment", payment);
                    return "CheckoutConfirmation";
                } catch (Exception e) {
                    System.err.println("Error saving payment: " + e.getMessage()); // Add error log
                    e.printStackTrace(); // Print the stack trace for detailed error information
                    // Consider redirecting to an error page or displaying an error message
                    return "redirect:/viewCart?error=checkout_failed";
                }
            } else {
                System.out.println("Payment already exists for this cart: " + existingPayment.getPaymentId()); // Add this log
                model.addAttribute("payment", existingPayment);
                return "CheckoutConfirmation";
            }
        } else {
            System.out.println("No cart found for customer: " + customer.getCustId()); // Add this log
            return "redirect:/viewCart";
        }
    }
    
    @RequestMapping("/displaypayments")
    public String displaypayments(Model model) {
    	
    	List<Payment> paylist = paymentRepo.findAll();
    	
    	model.addAttribute("paylist",paylist);
    	
    	return "Displaypayments";
    }
    
    @GetMapping("/deletepay/{paymentId}")	
   	public String deletepayment(@PathVariable int paymentId) {
   		
   		paymentRepo.deleteById(paymentId);
   		
   		return "redirect:/displayReservation";
   		
   	}
}