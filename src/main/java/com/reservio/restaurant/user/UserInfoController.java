package com.reservio.restaurant.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/userInfo", produces = "application/json")
public class UserInfoController {
    IUserInfoService userInfoService;
    UserInfoMapper userInfoMapper;

    public UserInfoController(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping
    public UserInfoResponse createUser(UserInfoRequest request) {
        return userInfoService.createUserInfo(request);
    }

    @GetMapping(path = "/{id}")
    public UserInfoResponse readUser(@PathVariable Long id) {
        return userInfoService.readUserInfo(id);
    }

    @PatchMapping(path = "/{id}")
    public UserInfoResponse updateUser(@PathVariable Long id, UserInfoRequest request) {
        return userInfoService.updateUserInfo(id, request);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Long id) {
        userInfoService.deleteUserInfo(id);
    }
}
