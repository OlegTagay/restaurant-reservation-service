package com.reservio.restaurant.dto.response.contactInfo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

public record ContactInfoResponse (
        Long id,
        @NotBlank(message = "Phone number is required") String phoneNumber,
        String address
) {
}
