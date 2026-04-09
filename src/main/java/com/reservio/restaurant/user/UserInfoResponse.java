package com.reservio.restaurant.user;

import com.reservio.restaurant.contact.ContactInfoResponse;

public record UserInfoResponse(
        Long id,
        String firstName,
        String lastName,
        ContactInfoResponse contactInfoResponse
) {
}