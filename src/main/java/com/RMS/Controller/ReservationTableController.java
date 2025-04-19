package com.RMS.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.RMS.Entity.Customers;
import com.RMS.Entity.DingingTable;
import com.RMS.Entity.Foods;
import com.RMS.Entity.ReservationTableEntity;
import com.RMS.Repository.DingingTableRepository;
import com.RMS.Repository.FoodsRepository;
import com.RMS.Repository.ReservationRepository;

import jakarta.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ReservationTableController {

    private static final Logger logger = LoggerFactory.getLogger(ReservationTableController.class);

    @Autowired
    private DingingTableRepository diningtavblerepo;

    @Autowired
    private ReservationRepository resereveRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private FoodsRepository foodrepo;

    @RequestMapping("/goreservetable")
    public String goreservedining(Model model, HttpSession session) {
        Customers customer = (Customers) session.getAttribute("customer");
        if (customer != null) {
            model.addAttribute("cutomername", customer.getCustName());
        }

        List<DingingTable> availableDiningTables = diningtavblerepo.findByTableAvailable("Not Booked");
        model.addAttribute("diningTables", availableDiningTables);

        return "Reservationtable";
    }


    @PostMapping("/savediingtable")
    public String savereservetable(@ModelAttribute ReservationTableEntity reservetable, HttpSession session, Model model) {
        logger.info("Saving reservation: {}", reservetable);
        // Get the logged-in customer from the session
        Customers customer = (Customers) session.getAttribute("customer");
        logger.info("Logged-in customer: {}", customer);
        reservetable.setCustomer(customer);

        // Save the reservation first
        ReservationTableEntity savedReservation = resereveRepo.save(reservetable);
        logger.info("Reservation saved with ID: {}", savedReservation.getReservationId());

        // Get the dining table associated with the reservation
        DingingTable dingingTableFromReservation = reservetable.getDiningTable();

        // Update the dining table status to "Booked" without modifying tableNumber and tablCapacity
        if (dingingTableFromReservation != null && dingingTableFromReservation.getTableId() > 0) {
            Optional<DingingTable> existingDingingTableOptional = diningtavblerepo.findById(dingingTableFromReservation.getTableId());
            if (existingDingingTableOptional.isPresent()) {
                DingingTable existingDingingTable = existingDingingTableOptional.get();
                logger.info("Updating dining table: {}", existingDingingTable);
                existingDingingTable.setTableAvailable("Booked");
                diningtavblerepo.save(existingDingingTable);
                logger.info("Dining table updated successfully.");

                // Send confirmation email
                sendReservationConfirmationEmail(savedReservation, customer, existingDingingTable);

            } else {
                logger.warn("Dining table with ID {} not found.", dingingTableFromReservation.getTableId());
            }
        }

        // Redirect to customer dashboard after successful reservation
        return "redirect:/csutomerpannel";
    }

    private void sendReservationConfirmationEmail(ReservationTableEntity reservation, Customers customer, DingingTable diningTable) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customer.getGmailId()); // Assuming Customers entity has getCustEmail()
        message.setSubject("Your Table Reservation Confirmation");
        message.setText(String.format(
                "Dear %s,\n\n" +
                        "Your table reservation has been confirmed.\n\n" +
                        "Reservation Details:\n" +
                        "Reservation ID: %d\n" +
                        "Table Number: %s\n" +
                        "Reservation Time: %s\n" +
                        "Number of Guests: %d\n\n" +
                        "Thank you for choosing our restaurant!\n\n" +
                        "Sincerely,\n" +
                        "The Restaurant Management Team",
                customer.getCustName(), // Assuming Customers entity has getCustName()
                reservation.getReservationId(),
                diningTable.getTableNumber(),
                reservation.getReservationTime(),
                reservation.getNumberOfGuests()
        ));

        mailSender.send(message);
        logger.info("Reservation confirmation email sent to: {}", customer.getGmailId());
    }

    @GetMapping("/logoutcustomer")
     public String logout(HttpSession session) {
      session.invalidate();
      return "redirect:/";
     }

    @GetMapping("/csutomerpannel")
    public String customerDashboard(Model model, HttpSession session) {
        Customers customer = (Customers) session.getAttribute("customer");
        logger.info("Fetching dashboard for customer: {}", customer);
        if (customer != null) {
            logger.info("Customer ID: {}", customer.getCustId()); // Assuming getCustId() exists
            List<ReservationTableEntity> myReservations = resereveRepo.findByCustomer(customer);
            logger.info("Reservations found for customer {}: {}", customer.getCustId(), myReservations);
            model.addAttribute("myReservations", myReservations);
            model.addAttribute("cutomername", customer.getCustName()); // Ensure customer name is still available

            // Fetch food data again to ensure it's available on the dashboard
            List<Foods> listfoodsdata = foodrepo.findAll();
            model.addAttribute("listfoodsdata", listfoodsdata);

            return "CustomerDashabord";
        } else {
            logger.warn("Customer not found in session, redirecting to login.");
            return "redirect:/logincutomer";
        }
    }


    @GetMapping("/Deletefreservetab/{reservationId}")
    public String deletefoodcate(@PathVariable int reservationId) {

        Optional<ReservationTableEntity> reservationOptional = resereveRepo.findById(reservationId);

        if (reservationOptional.isPresent()) {
            ReservationTableEntity reservation = reservationOptional.get();
            Customers customer = reservation.getCustomer(); // Get the customer associated with the reservation
            DingingTable dingingTable = reservation.getDiningTable();

            resereveRepo.deleteById(reservationId);
            logger.info("Reservation deleted with ID: {}", reservationId);

            // Send cancellation email
            if (customer != null) {
                sendReservationCancellationEmail(customer, reservation, dingingTable);
            } else {
                logger.warn("Customer information not found for reservation ID: {}", reservationId);
            }

            if (dingingTable != null) {
                Optional<DingingTable> dingingTableOptional = diningtavblerepo.findById(dingingTable.getTableId());
                if (dingingTableOptional.isPresent()) {
                    DingingTable existingDingingTable = dingingTableOptional.get();
                    existingDingingTable.setTableAvailable("Not Booked");
                    diningtavblerepo.save(existingDingingTable);
                    logger.info("Dining table with ID {} updated to 'Not Booked'.", existingDingingTable.getTableId());
                } else {
                    logger.warn("Dining table with ID {} not found while updating status after reservation deletion.", dingingTable.getTableId());
                }
            } else {
                logger.warn("Dining table information not found for reservation ID: {}", reservationId);
            }
        } else {
            logger.warn("Reservation with ID {} not found for deletion.", reservationId);
        }

        return "redirect:/csutomerpannel";

    }

    private void sendReservationCancellationEmail(Customers customer, ReservationTableEntity reservation, DingingTable diningTable) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(customer.getGmailId()); // Use the correct email field
        message.setSubject("Your Table Reservation has been Cancelled");
        message.setText(String.format(
                "Dear %s,\n\n" +
                        "Your table reservation has been cancelled.\n\n" +
                        "Reservation Details:\n" +
                        "Reservation ID: %d\n" +
                        "Table Number: %s\n" +
                        "Reservation Time: %s\n" +
                        "Number of Guests: %d\n\n" +
                        "We apologize for any inconvenience this may cause.\n\n" +
                        "Sincerely,\n" +
                        "The Restaurant Management Team",
                customer.getCustName(),
                reservation.getReservationId(),
                (diningTable != null) ? diningTable.getTableNumber() : "N/A", // Handle case where dining table might be null
                reservation.getReservationTime(),
                reservation.getNumberOfGuests()
        ));

        mailSender.send(message);
        logger.info("Reservation cancellation email sent to: {}", customer.getGmailId());
    }
    
    @RequestMapping("/displayReservationdata")
    public String displayreservtaion(Model model) {
    	
    	List<ReservationTableEntity> reservateionlist = resereveRepo.findAll();
    	
    	 model.addAttribute("reservateionlist",reservateionlist);
    	
    	return "DisplayReservation";
    	
    }

    
    @GetMapping("/deletereser/{reservationId}")	
	public String deletereerbvation(@PathVariable int reservationId) {
		
    	resereveRepo.deleteById(reservationId);
		
		return "redirect:/displayReservation";
		
	}

}