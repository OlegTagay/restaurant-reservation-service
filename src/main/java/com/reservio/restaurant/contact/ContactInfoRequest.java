package com.reservio.restaurant.contact;

import jakarta.validation.constraints.NotNull;

public record ContactInfoRequest(
        @NotNull(message = "Phone number is required")
        String phoneNumber,
        String address
) {
}

