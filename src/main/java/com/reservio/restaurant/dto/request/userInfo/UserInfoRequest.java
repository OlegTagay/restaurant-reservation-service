package com.reservio.restaurant.dto.request.userInfo;

import com.reservio.restaurant.dto.request.contactInfo.CreateContactInfoRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserInfoRequest {
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotNull
    private CreateContactInfoRequest createContactInfoRequest;
}
