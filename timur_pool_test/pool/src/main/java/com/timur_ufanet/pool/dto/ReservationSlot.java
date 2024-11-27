package com.timur_ufanet.pool.dto;

import java.time.LocalDateTime;

public class ReservationSlot {

    private LocalDateTime time;  // Время слота
    private int count;           // Количество занятых мест

    // Конструктор
    public ReservationSlot(LocalDateTime time, int count) {
        this.time = time;
        this.count = count;
    }

    // Геттеры и сеттеры
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
