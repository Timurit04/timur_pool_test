package com.timur_ufanet.pool.dto;

import java.time.LocalDateTime;

public class AvailableSlot {

    private LocalDateTime time;  // Время слота
    private int availableCount;  // Количество доступных мест

    // Конструктор
    public AvailableSlot(LocalDateTime time, int availableCount) {
        this.time = time;
        this.availableCount = availableCount;
    }

    // Геттеры и сеттеры
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }
}
