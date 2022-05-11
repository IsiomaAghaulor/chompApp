package com.decagon.chompapp.controllers;

import com.decagon.chompapp.dtos.*;
import com.decagon.chompapp.services.ProductServices;
import com.decagon.chompapp.services.UserService;
import com.decagon.chompapp.utils.AppConstants;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;


@RestController
@AllArgsConstructor
@RequestMapping( "/api/v1/auth/users")
public class UserController {

    private final UserService userService;

    private final ProductServices productServices;

    @PutMapping("/edit")
    public ResponseEntity<String> editUserDetails(@Valid @RequestBody EditUserDto editUserDto) {
        return userService.editUserDetails(editUserDto);
    }

    @GetMapping("/getAllProducts")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(value = "filterBy", defaultValue = AppConstants.DEFAULT_FILTER_BY_PARAMETER, required = false) String filterBy,
            @RequestParam(value = "filterParam", defaultValue = AppConstants.DEFAULT_FILTER_PARAMETER, required = false) String filterParam)
            throws ServletException {
        return productServices.getAllProducts(pageNo, pageSize, sortBy, sortDir, filterBy, filterParam);
    }
    @PutMapping("/password-update")
    public ResponseEntity<String> login(@RequestBody PasswordDto passwordDto) {
        return userService.updatePassword(passwordDto);
    }

    @PostMapping("/generate-token")
    public ResponseEntity<String> generateToken(@RequestBody EmailSenderDto emailSenderDto) throws MessagingException {
        return new ResponseEntity<>(userService.generateResetToken(emailSenderDto.getTo()), OK);
    }

    @GetMapping("/enter-password")
    public ResponseEntity<String> enterNewPassword(@RequestParam("token") String token) {
        return new ResponseEntity<>("Please enter new password", OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto, @RequestParam("token") String token) {
        return new ResponseEntity<>(userService.resetPassword(resetPasswordDto, token), OK);
    }
}
