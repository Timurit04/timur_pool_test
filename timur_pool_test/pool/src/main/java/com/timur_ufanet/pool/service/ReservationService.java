package com.timur_ufanet.pool.service;


import com.timur_ufanet.pool.dto.AvailableSlot;
import com.timur_ufanet.pool.dto.ReservationSlot;
import com.timur_ufanet.pool.entity.Reservation;
import com.timur_ufanet.pool.entity.Client;
import com.timur_ufanet.pool.repository.ReservationRepository;
import com.timur_ufanet.pool.repository.ClientRepository;
import com.timur_ufanet.pool.dto.ReservationRequest;
import com.timur_ufanet.pool.dto.CancelRequest;
import com.timur_ufanet.pool.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientRepository clientRepository;

    // Получение всех записей на определенную дату
    public List<ReservationSlot> getReservationsForDate(String date) {
        // Преобразуем строку даты в LocalDateTime
        LocalDateTime startOfDay = LocalDateTime.parse(date + "T00:00:00");
        LocalDateTime endOfDay = LocalDateTime.parse(date + "T23:59:59");

        // Получаем все бронирования для данного интервала времени
        List<Reservation> reservations = reservationRepository.findByReservationTimeBetween(startOfDay, endOfDay);

        // Преобразуем сущности Reservation в DTO ReservationSlot для передачи клиенту
        List<ReservationSlot> reservationSlots = new ArrayList<>();
        for (Reservation reservation : reservations) {
            reservationSlots.add(new ReservationSlot(reservation.getReservationTime(), 1));  // Здесь 1 — это пример, нужно уточнить логику для count
        }

        return reservationSlots;
    }

    public List<AvailableSlot> getAvailableSlots(String date) {
        // Преобразуем строку даты в LocalDateTime
        LocalDateTime startOfDay = LocalDateTime.parse(date + "T00:00:00");
        LocalDateTime endOfDay = LocalDateTime.parse(date + "T23:59:59");

        // Предположим, что максимальное количество записей на слот = 10
        int maxSlots = 10;

        // Получаем все бронирования для данной даты
        List<Reservation> reservations = reservationRepository.findByReservationTimeBetween(startOfDay, endOfDay);

        // Создаем список для доступных слотов
        List<AvailableSlot> availableSlots = new ArrayList<>();

        // Проходим по всем временным слотам (например, от 00:00 до 23:00 с шагом 1 час)
        for (int hour = 0; hour < 24; hour++) {
            LocalDateTime slotTime = startOfDay.plusHours(hour);

            // Подсчитываем количество занятых мест на данный слот
            long occupied = reservations.stream().filter(r -> r.getReservationTime().equals(slotTime)).count();

            // Если количество занятых мест меньше максимального, слот доступен
            if (occupied < maxSlots) {
                availableSlots.add(new AvailableSlot(slotTime, maxSlots - (int) occupied));
            }
        }

        return availableSlots;
    }


    // Проверка доступных мест и создание записи
    public Reservation reserveSlot(ReservationRequest request) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Клиент не найден с id: " + request.getClientId()));

        // Проверка на занятость слота и лимит записей
        long count = reservationRepository.countByReservationTime(request.getDatetime());
        if (count >= 10) {
            throw new IllegalStateException("На это время уже записано максимальное количество клиентов");
        }

        // Создание записи
        Reservation reservation = new Reservation();
        reservation.setClient(client);
        reservation.setReservationTime(request.getDatetime());

        return reservationRepository.save(reservation);
    }

    // Отмена записи клиента
    public void cancelReservation(CancelRequest request) {
        Reservation reservation = reservationRepository.findByIdAndClientId(request.getOrderId(), request.getClientId());


        reservationRepository.delete(reservation);
    }
}

