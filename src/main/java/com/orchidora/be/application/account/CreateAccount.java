package com.orchidora.be.application.account;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CreateAccount {

    @Data
    public static final class CreateAccountRequest {

        @NotBlank
        private String email;

        @NotBlank
        private String password;
    }

    @RestController
    @RequiredArgsConstructor
    @Tag(name = "accounts")
    public static final class Controller {

        private static Service service;

        public ResponseEntity<Object> createAccount() {
            return null;
        }
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    public static class Service {
    }
}
