package com.RMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.RMS.Entity.Customers;
import com.RMS.Entity.DingingTable;
import com.RMS.Entity.Foods;
import com.RMS.Entity.ReservationTableEntity;
import com.RMS.Repository.CustomerRepository;
import com.RMS.Repository.DingingTableRepository;
import com.RMS.Repository.FoodsRepository;
import com.RMS.Repository.ReservationRepository;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.mail.MailException;

@Controller
public class CustomerController {

  private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

  @Autowired
  private FoodsRepository foodrepo;

  @Autowired
  private CustomerRepository custoRepo;

  @Autowired
  private DingingTableRepository dingingtablerepo;

  @Autowired
  private ReservationRepository reservationRepository;

  @Autowired
  private JavaMailSender javaMailSender;

  @RequestMapping("/datadingingtables")
  public String displaydingingtbls(Model model){

    List<DingingTable> dingingtable = dingingtablerepo.findAll();

    model.addAttribute("displaydining", dingingtable);

    return "DiningTables";

  }

  @GetMapping("/registerpage")
  public String goRegisterPage(Model model) {
    return "RegisterCustomer";
  }

  @GetMapping("/logincustomer")
  public String goLoginCustomer() {
    return "Customerlogin";
  }

  @PostMapping("/addsavecustomer")
  public String saveCustomer(@ModelAttribute Customers customer, Model model) {
    Optional<Customers> existingCustomer = custoRepo.findByGmailId(customer.getGmailId());
    if (existingCustomer.isPresent()) {
      model.addAttribute("errorMessage", "Email ID already exists. Please use a different email.");
      return "RegisterCustomer";
    }
    custoRepo.save(customer);
    model.addAttribute("successmessage", "Registration successful! Please log in.");
    return "Customerlogin";
  }

  @PostMapping("/logincustomer")
  public String loginCustomer(@RequestParam("gmailId") String gmailId,
                  @RequestParam("password") String password,
                  Model model,
                  HttpSession session) {
    Optional<Customers> optionalCustomer = custoRepo.findByGmailId(gmailId);

    if (optionalCustomer.isPresent()) {
      Customers customer = optionalCustomer.get();

      if (customer.getPassword().equals(password)) {
        List<Foods> listfoodsdata = foodrepo.findAll();
        model.addAttribute("listfoodsdata", listfoodsdata);
        session.setAttribute("customer", customer);
        logger.info("Customer logged in successfully via password: {}", customer.getCustName());
        sendLoginNotificationEmail(customer);
        return "redirect:/gobackcusdash";
      } else {
        logger.warn("Invalid password attempt for Gmail ID: {}", gmailId);
        model.addAttribute("errorMessage", "Invalid Email-ID or Password");
        return "Customerlogin";
      }
    }
    logger.warn("Invalid login attempt for Gmail ID: {}", gmailId);
    model.addAttribute("errorMessage", "Invalid Email-ID or Password");
    return "Customerlogin";
  }

  private void sendLoginNotificationEmail(Customers customer) {
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(customer.getGmailId());
      message.setSubject("Login Notification - Restronment Management System");

      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss 'IST'");
      String loginDateTime = now.format(formatter);

      String body = "Dear " + customer.getCustName() + ",\n\n" +
          "You have successfully logged into the Restronment Management System on " + loginDateTime + ".\n\n" +
          "Thank you for using our service!\n\n" +
          "Sincerely,\n" +
          "The Restronment Management System Team";

      message.setText(body);

      javaMailSender.send(message);
      logger.info("Login notification email sent to: {}", customer.getGmailId());

    } catch (MailException ex) {
      logger.error("Error sending login notification email to {}: {}", customer.getGmailId(), ex.getMessage());
    }
  }

  @RequestMapping("/gobackcusdash")
  public String gobackcustomerdash(Model model, HttpSession session) {
    List<Foods> listfoodsdata = foodrepo.findAll();
    model.addAttribute("listfoodsdata", listfoodsdata);

    Customers customer = (Customers) session.getAttribute("customer");
    if (customer != null) {
      model.addAttribute("cutomername", customer.getCustName());
      List<ReservationTableEntity> myReservations = reservationRepository.findByCustomer(customer);
      model.addAttribute("myReservations", myReservations);
    }


    return "CustomerDashabord";
  }

  @GetMapping("/logoutcutomer")
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/";
  }

  @RequestMapping("/displayusersdata")
  public String displayuers(Model model) {

    List<Customers> userlist = custoRepo.findAll();

    model.addAttribute("userlist",userlist);

    return "Displayusers";


  }

  @GetMapping("/deletefoodCate/{custId}")
  public String deletefoodcate(@PathVariable int custId) {

    custoRepo.deleteById(custId);

    return "redirect:/displayusersdata";

  }
}