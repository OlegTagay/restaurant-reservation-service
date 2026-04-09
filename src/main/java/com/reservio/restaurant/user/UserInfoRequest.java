package com.reservio.restaurant.user;

import com.reservio.restaurant.contact.ContactInfoRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UserInfoRequest(
        @NotNull(message = "First name is required")
        String firstName,
        @NotNull(message = "Last name is required")
        String lastName,
        @Valid
        @NotNull
        ContactInfoRequest contactInfoRequest
) {
}
