package com.example.cloudbeesproject.cloudbeesproject;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TicketController {
    private Map<String, TicketPurchase> ticketPurchases = new HashMap<>();

    @PostMapping("/purchaseTicket")
    public void purchaseTicket(@RequestBody TicketPurchase ticketPurchase) {
        ticketPurchases.put(ticketPurchase.getEmail(), ticketPurchase);
    }

    @GetMapping("/usersAndSeats/{section}")
    public Map<String, String> getUsersAndSeatsBySection(@PathVariable String section) {
        Map<String, String> usersAndSeats = new HashMap<>();
        for (TicketPurchase ticketPurchase : ticketPurchases.values()) {
            if (isInSection(ticketPurchase, section)) {
                usersAndSeats.put(ticketPurchase.getEmail(), ticketPurchase.getSeat());
            }
        }
        return usersAndSeats;
    }

    @GetMapping("/receiptDetails/{email}")
    public ResponseEntity<Map<String, Object>> getReceiptDetailsByEmail(@PathVariable String email) {
        TicketPurchase ticketPurchase = ticketPurchases.get(email);
        if (ticketPurchase == null) {
            return ResponseEntity.notFound().build();
        }

        Map<String, Object> receiptDetails = new HashMap<>();
        receiptDetails.put("from", ticketPurchase.getFrom());
        receiptDetails.put("to", ticketPurchase.getTo());
        receiptDetails.put("price", ticketPurchase.getPrice());
        receiptDetails.put("seat", ticketPurchase.getSeat());

        return ResponseEntity.ok(receiptDetails);
    }

    @DeleteMapping("/removeUser/{email}")
    public ResponseEntity<String> removeUser(@PathVariable String email) {
        TicketPurchase removed = ticketPurchases.remove(email);
        if (removed == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("User removed successfully");
    }

    @PutMapping("/modifySeat/{email}")
    public ResponseEntity<String> modifySeat(@PathVariable String email, @RequestBody String newSeat) {
        TicketPurchase ticketPurchase = ticketPurchases.get(email);
        if (ticketPurchase == null) {
            return ResponseEntity.notFound().build();
        }

        ticketPurchase.setSeat(newSeat);
        ticketPurchases.put(email, ticketPurchase);

        return ResponseEntity.ok("Seat modified successfully");
    }

    private boolean isInSection(TicketPurchase ticketPurchase, String section) {
        String seatNumber = ticketPurchase.getSeat();
        if (seatNumber != null && seatNumber.length() > 0) {
            return seatNumber.substring(0, 1).equalsIgnoreCase(section);
        }
        return false;
    }
}
