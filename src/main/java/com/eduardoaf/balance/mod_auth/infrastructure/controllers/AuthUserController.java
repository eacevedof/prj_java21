package com.eduardoaf.balance.mod_auth.infrastructure.controllers;

import com.eduardoaf.balance.mod_auth.application.dtos.AuthUserDto;
import com.eduardoaf.balance.mod_auth.application.services.AuthUserService;
import com.eduardoaf.balance.mod_auth.domain.exceptions.AuthUserException;
import com.eduardoaf.balance.mod_shared.domain.exceptions.DomainTypeException;
import com.eduardoaf.balance.mod_shared.domain.exceptions.DomainValueException;
import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_shared.infrastructure.http.responses.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthUserController {

    private final Log log;
    private final AuthUserService authUserService;
    private final HttpResponse httpResponse;

    @Autowired
    public AuthUserController(
        Log log,
        AuthUserService authUserService,
        HttpResponse httpResponse
    ) {
        this.log = log;
        this.authUserService = authUserService;
        this.httpResponse = httpResponse;
    }

    @PostMapping(value = "api/v1/user/auth", consumes = {"application/json"})
    public ResponseEntity<?> authUser(@RequestBody AuthUserDto authUserDto) {
        try {
            authUserDto = AuthUserDto.getInstance(
                authUserDto.username(),
                authUserDto.password()
            );
            var createdUserDto = authUserService.invoke(authUserDto);
            return httpResponse.getResponse201("User logged", createdUserDto);
        }
        catch (DomainTypeException | DomainValueException | AuthUserException e) {
            return httpResponse.getResponse400(e.getMessage());
        }
        catch (Exception e) {
            log.exception(e);
            return httpResponse.getResponse500("some unexpected error occurred");
        }
    }
}