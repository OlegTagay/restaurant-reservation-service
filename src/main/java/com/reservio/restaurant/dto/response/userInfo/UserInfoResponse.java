package com.reservio.restaurant.dto.response.userInfo;

import com.reservio.restaurant.dto.response.contactInfo.ContactInfoResponse;

public record UserInfoResponse(
        Long id,
        String firstName,
        String lastName,
        ContactInfoResponse contactInfoResponse
) {
}