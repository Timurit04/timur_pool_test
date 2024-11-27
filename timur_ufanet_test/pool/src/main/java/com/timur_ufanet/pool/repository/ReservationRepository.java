package com.timur_ufanet.pool.repository;

import com.timur_ufanet.pool.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Метод для поиска всех записей на определенную дату
    List<Reservation> findByReservationTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    // Метод для подсчета количества записей на определенное время
    long countByReservationTime(LocalDateTime datetime);

    // Метод для поиска записи по ID и ID клиента
    Reservation findByIdAndClientId(Long orderId, Long clientId);
}
