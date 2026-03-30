package com.reservio.restaurant.dto.request.contactInfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record UpdateContactInfoRequest (
        @NotBlank(message = "Phone number is required") String phoneNumber,
        String address
) {
}