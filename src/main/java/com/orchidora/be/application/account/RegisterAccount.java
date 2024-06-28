package com.orchidora.be.application.account;

import com.orchidora.be.application.account.response.AccountResponse;
import com.orchidora.be.application.common.OrchidoraController;
import com.orchidora.be.application.common.OrchidoraMapper;
import com.orchidora.be.entity.account.Account;
import com.orchidora.be.entity.account.Role;
import com.orchidora.be.entity.common.ActiveStatus;
import com.orchidora.be.infrastructure.repository.AccountRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegisterAccount {

    @Getter
    @Setter
    @NoArgsConstructor
    public static final class CreateAccountRequest {

        @NotBlank
        private String email;

        @NotBlank
        private String password;

        @NotBlank
        private String name;

        @NotBlank
        @Pattern(regexp = "^\\d{11}$", message = "must be 11-digits")
        private String phoneNumber;

        @NotBlank
        private String address;
    }

    @RestController
    @RequiredArgsConstructor
    @Tag(name = "accounts")
    public static final class RegisterAccountController extends OrchidoraController {

        private final RegisterAccountService service;

        @PostMapping("/accounts")
        public ResponseEntity<AccountResponse> register(@Valid @RequestBody CreateAccountRequest request) {
            try {
                final AccountResponse response = service.register(request);
                return ResponseEntity.ok(response);
            } catch (DataIntegrityViolationException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        String.format("Account with email %s already exists", request.getEmail()), e);
            }
        }
    }

    @Service
    @Transactional
    @RequiredArgsConstructor
    public static class RegisterAccountService {

        private final AccountRepository accountRepository;
        private final OrchidoraMapper<Account, AccountResponse> orchidoraMapper;
        private final PasswordEncoder passwordEncoder;

        public AccountResponse register(CreateAccountRequest request) {
            Account account = Account.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .phoneNumber(request.getPhoneNumber())
                    .address(request.getAddress())
                    .role(Role.USER)
                    .status(ActiveStatus.ACTIVE)
                    .build();
            account = accountRepository.save(account);
            return orchidoraMapper.toDto(account);
        }
    }
}
