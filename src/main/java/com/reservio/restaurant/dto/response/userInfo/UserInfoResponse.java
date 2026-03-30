package com.reservio.restaurant.dto.response.userInfo;

import com.reservio.restaurant.dto.response.contactInfo.ContactInfoResponse;
import lombok.Data;

public record UserInfoResponse (
    String firstName,
    String lastName,
    ContactInfoResponse contactInfoResponse
) {}