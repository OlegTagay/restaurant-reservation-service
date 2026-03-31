package com.reservio.restaurant.dto.response.contactInfo;

import jakarta.validation.constraints.NotBlank;

public record ContactInfoResponse (
        Long id,
        String phoneNumber,
        String address
) {
}
