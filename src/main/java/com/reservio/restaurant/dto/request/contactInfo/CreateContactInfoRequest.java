package com.reservio.restaurant.dto.request.contactInfo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public record CreateContactInfoRequest(
        @NotBlank(message = "Phone number is required") String phoneNumber,
        String address
) {
}

