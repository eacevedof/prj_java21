package com.eduardoaf.balance.mod_users.infrastructure.controllers;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.eduardoaf.balance.mod_shared.infrastructure.file.Log;
import com.eduardoaf.balance.mod_shared.infrastructure.http.responses.HttpResponse;
import com.eduardoaf.balance.mod_shared.domain.exceptions.AbstractDomainException;
import com.eduardoaf.balance.mod_shared.domain.exceptions.DomainTypeException;
import com.eduardoaf.balance.mod_shared.domain.exceptions.DomainValueException;
import com.eduardoaf.balance.mod_shared.domain.services.DomainAuthService;

import com.eduardoaf.balance.mod_users.application.dtos.CreateUserDto;
import com.eduardoaf.balance.mod_users.application.services.CreateUserService;
import com.eduardoaf.balance.mod_users.domain.exceptions.CreateUserException;

@RestController
public class CreateUserController {

    private final Log log;
    private final DomainAuthService domainAuthService;
    private final CreateUserService createUserService;
    private final HttpResponse httpResponse;

    @Autowired
    public CreateUserController(
        Log log,
        DomainAuthService domainAuthService,
        CreateUserService createUserService,
        HttpResponse httpResponse
    ) {
        this.log = log;
        this.domainAuthService = domainAuthService;
        this.createUserService = createUserService;
        this.httpResponse = httpResponse;
    }

    @PostMapping(value = "api/v1/user/create", consumes = {"application/json"})
    public ResponseEntity<?> createUser(
        HttpServletRequest httpRequest,
        @RequestBody CreateUserDto createUserDto
    ) {
        try {
            var jwt = httpRequest.getHeader("Authorization");
            domainAuthService.tryToLoadAuthUserByJwtOrFail(jwt);

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
                createUserDto.idProfile(),
                domainAuthService.getAuthUser().getId().toString(),
                "1"
            );
            var createdUserDto = createUserService.invoke(createUserDto);
            return httpResponse.getResponse201("User created", createdUserDto);
        }
        catch (AbstractDomainException | DomainTypeException | DomainValueException | CreateUserException e) {
            return httpResponse.getResponse400(e.getMessage());
        }
        catch (Exception e) {
            log.exception(e);
            return httpResponse.getResponse500("some unexpected error occurred");
        }
    }
}