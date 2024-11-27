package com.timur_ufanet.pool.dto;

import java.time.LocalDateTime;

import lombok.*;


@Getter
@Setter
public class ReservationRequest {
    private Long clientId;
    private LocalDateTime datetime;

}