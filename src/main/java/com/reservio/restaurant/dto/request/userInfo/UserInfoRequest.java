package com.reservio.restaurant.dto.request.userInfo;

import com.reservio.restaurant.dto.request.contactInfo.CreateContactInfoRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record UserInfoRequest(
        @NotBlank(message = "First name is required") String firstName,
        @NotBlank(message = "Last name is required") String lastName,
        @NotNull CreateContactInfoRequest createContactInfoRequest
) {
}
