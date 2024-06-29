package com.orchidora.be.application.account;

import com.orchidora.be.application.account.response.AccountResponse;
import com.orchidora.be.application.common.OrchidoraController;
import com.orchidora.be.application.common.OrchidoraMapper;
import com.orchidora.be.application.exception.OrchidoraException;
import com.orchidora.be.entity.account.Account;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Login {

    @Getter
    @Setter
    @NoArgsConstructor
    public static final class LoginRequest {

        @NotBlank
        @Email
        private String email;

        @NotBlank
        private String password;
    }

    @RestController
    @RequiredArgsConstructor
    @Tag(name = "auth")
    public static final class LoginController extends OrchidoraController {

        private final LoginService service;

        @PostMapping("/accounts/auth")
        public ResponseEntity<AccountResponse> login(@Valid @RequestBody LoginRequest request) {
            final AccountResponse response = service.login(request);
            return ResponseEntity.ok(response);
        }
    }

    @Service
    @Transactional(rollbackOn = Exception.class)
    @RequiredArgsConstructor
    public static class LoginService {

        private final AuthenticationManager authenticationManager;
        private final OrchidoraMapper<Account, AccountResponse> accountMapper;

        public AccountResponse login(LoginRequest request) {
            Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(),
                    request.getPassword());
            authentication = authenticationManager.authenticate(authentication);
            final Object principal = authentication.getPrincipal();
            if (principal instanceof Account account) {
                return accountMapper.toDto(account);
            }
            throw new OrchidoraException(HttpStatus.BAD_REQUEST, "account", "Unknown account");
        }
    }
}
