package com.timur_ufanet.pool.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.timur_ufanet.pool.dto.AvailableSlot;
import com.timur_ufanet.pool.dto.CancelRequest;
import com.timur_ufanet.pool.dto.ReservationRequest;
import com.timur_ufanet.pool.dto.ReservationSlot;
import com.timur_ufanet.pool.entity.Client;
import com.timur_ufanet.pool.repository.ClientRepository;
import com.timur_ufanet.pool.service.ReservationService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/pool/timetable")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/all")
    public List<ReservationSlot> getAllReservations(@RequestParam String date) {
        return reservationService.getReservationsForDate(date);
    }

    @GetMapping("/available")
    public List<AvailableSlot> getAvailableSlots(@RequestParam String date) {
        return reservationService.getAvailableSlots(date);
    }

    @PostMapping("/reserve")
    public String reserveSlot(@RequestBody ReservationRequest request) {
        reservationService.reserveSlot(request); // Попытка бронирования
        return "Reservation successful"; // Возвращаем просто строку
    }

    @GetMapping("/cancel")
    public String cancelReservation(@RequestBody CancelRequest request) {
        reservationService.cancelReservation(request); // Попытка отмены бронирования
        return "Reservation canceled successfully"; // Возвращаем просто строку
    }

}
