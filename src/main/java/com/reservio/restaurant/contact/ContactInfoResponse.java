package com.reservio.restaurant.contact;

public record ContactInfoResponse (
        Long id,
        String phoneNumber,
        String address
) {
}
