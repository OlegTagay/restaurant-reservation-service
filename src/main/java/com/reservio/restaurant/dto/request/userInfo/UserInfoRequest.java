package com.reservio.restaurant.dto.request.userInfo;

import com.reservio.restaurant.dto.request.contactInfo.ContactInfoRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserInfoRequest(
        @NotBlank(message = "First name is required")
        String firstName,
        String lastName,
        @NotNull
        ContactInfoRequest contactInfoRequest
) {
}
