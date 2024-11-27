package com.timur_ufanet.pool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)  // Возвращает 404 статус при выбросе исключения
public class ResourceNotFoundException extends RuntimeException {

    // Конструктор с сообщением об ошибке
    public ResourceNotFoundException(String message) {
        super(message);
    }
}