package com.eduardoaf.balance.sys_users.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.eduardoaf.balance.shared.infrastructure.file.Log;
import com.eduardoaf.balance.shared.infrastructure.http.responses.HttpResponse;
import com.eduardoaf.balance.sys_users.application.dtos.CreateUserDto;
import com.eduardoaf.balance.sys_users.application.services.CreateUserService;
import com.eduardoaf.balance.sys_users.domain.exceptions.CreateUserException;

@RestController
public class CreateUserController {

    private final Log log;
    private final CreateUserService createUserService;
    private final HttpResponse httpResponse;

    @Autowired
    public CreateUserController(
        Log log,
        CreateUserService createUserService,
        HttpResponse httpResponse
    ) {
        this.log = log;
        this.createUserService = createUserService;
        this.httpResponse = httpResponse;
    }

    @PostMapping(value = "api/v1/create-user", consumes = {"application/json"})
    public ResponseEntity<?> createUser(@RequestBody CreateUserDto createUserDto) {
        try {
            createUserDto = CreateUserDto.getInstance(
                    createUserDto.codeErp(),
                    createUserDto.email(),
                    createUserDto.secret(),
                    createUserDto.phone(),
                    createUserDto.fullname(),
                    createUserDto.address(),
                    createUserDto.birthdate(),
                    createUserDto.idParent(),
                    createUserDto.idGender(),
                    createUserDto.idNationality(),
                    createUserDto.idCountry(),
                    createUserDto.idLanguage(),
                    createUserDto.idProfile()
            );
            var createdUserDto = createUserService.invoke(createUserDto);
            return httpResponse.getResponse200("entity created", createdUserDto);
        }
        catch (CreateUserException e) {
            return httpResponse.getResponse400(e.getMessage());
        }
        catch (Exception e) {
            log.exception(e);
            return httpResponse.getResponse500("some unexpected error occurred");
        }
    }
}