package com.devPortes.users.dto.client;


import org.springframework.web.multipart.MultipartFile;

public record EditClientRequestDto(
        MultipartFile picture,
        String name,
        String phoneNumber,
        int reservationAmount

) {
}
