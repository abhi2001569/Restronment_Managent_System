package com.RMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.RMS.Repository.CartRepository;
import com.RMS.Repository.CustomerRepository;
import com.RMS.Repository.DingingTableRepository;
import com.RMS.Repository.FoodCategoryRepository;
import com.RMS.Repository.FoodsRepository;
import com.RMS.Repository.PaymentRepository;
import com.RMS.Repository.ReservationRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {


	@Value("${admin.name}")
    private String adminusername; 

    @Value("${admin.password}")
    private String adminpassword;
    
    @Autowired
    private FoodCategoryRepository Foodcaterepo;
    
    @Autowired
	private DingingTableRepository dingingtablerepo;
    
    @Autowired
	private CustomerRepository customerrepo;
    
    @Autowired
	private ReservationRepository reserverepo;
    
    @Autowired
	private FoodsRepository foodrepo;
    
    @Autowired
	private PaymentRepository payrepo;
    
    @Autowired
	private CartRepository cartrepo;
    
	
	@RequestMapping("/")
	public String HomeDashbaord() {
		
		return "Home";
	}
	
	@RequestMapping("/Adminlogin")
	public String GoAdminLogin() {
		
		return "AdminLogin";
	}
	
	@RequestMapping("/tableaddform")
	public String goaddtable() {
		
		return "AddDingingTable";
	}
	
	@PostMapping("/loginadminpanel")
	public String loginAdminPanel(@RequestParam("adminusername")  String adminusername, @RequestParam("adminpassword") String adminpassword, Model model, HttpSession session) {

		if(this.adminusername.equals(adminusername) && this.adminpassword.equals(adminpassword))
		{
			
			long FoodCatecount = Foodcaterepo.count();
			
			long diningtablecount = dingingtablerepo.count();
			model.addAttribute("diningtablecount", diningtablecount);
			
			long userscount = customerrepo.count();
			model.addAttribute("userscount", userscount);
			
			long tablereservationcount = reserverepo.count();
			model.addAttribute("tablereservationcount", tablereservationcount);
			
			long foodcount = foodrepo.count();
			model.addAttribute("foodcount", foodcount);
			
			long paymentcount = payrepo.count();
			model.addAttribute("paymentcount", paymentcount);
			
			long cartcount = cartrepo.count();
			model.addAttribute("cartcount", cartcount);
			
			
			
			session.setAttribute("adminName", this.adminusername); 
			
			model.addAttribute("FoodCatecount",FoodCatecount);
			
			return "AdminDashBaord";
		}else {

			model.addAttribute("errorMessage", "Invalid Email-ID or Password");
 			return "AdminLogin";
		}

	}
	
	@GetMapping("/logoutadmin")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/"; 
    }
	
	@RequestMapping("/admindashbaord")
	public String GoAdminDashboard(Model model) {
		

		long FoodCatecount = Foodcaterepo.count();
		
		long diningtablecount = dingingtablerepo.count();
		model.addAttribute("diningtablecount", diningtablecount);
		
		long userscount = customerrepo.count();
		model.addAttribute("userscount", userscount);
		
		long tablereservationcount = reserverepo.count();
		model.addAttribute("tablereservationcount", tablereservationcount);
		
		long foodcount = foodrepo.count();
		model.addAttribute("foodcount", foodcount);
		
		long paymentcount = payrepo.count();
		model.addAttribute("paymentcount", paymentcount);
		
		long cartcount = cartrepo.count();
		model.addAttribute("cartcount", cartcount);
		
		
		return "AdminDashBaord";
	}

}
