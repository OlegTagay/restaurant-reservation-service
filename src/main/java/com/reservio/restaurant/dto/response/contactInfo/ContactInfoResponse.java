package com.reservio.restaurant.dto.response.contactInfo;

import jakarta.validation.constraints.NotBlank;

public record ContactInfoResponse (
        Long id,
        @NotBlank(message = "Phone number is required") String phoneNumber,
        String address
) {
}
