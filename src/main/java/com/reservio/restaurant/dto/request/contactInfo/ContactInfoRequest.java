package com.reservio.restaurant.dto.request.contactInfo;

import jakarta.validation.constraints.NotBlank;

public record ContactInfoRequest(
        @NotBlank(message = "Phone number is required")
        String phoneNumber,
        String address
) {
}

