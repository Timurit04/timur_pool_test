package com.timur_ufanet.pool.dto;

import lombok.*;


@Getter
@Setter

public class CancelRequest {
    private Long clientId;
    private Long orderId;
}
