package com.orchidora.be.infrastructure.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orchidora.be.application.exception.OrchidoraException;
import com.orchidora.be.entity.account.Account;
import com.orchidora.be.infrastructure.error.OrchidoraErrorResponse;
import com.orchidora.be.infrastructure.repository.AccountRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

    private final AccountRepository accountRepository;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws IOException, ServletException {
        try {
            final String bearerToken = request.getHeader("Authorization");
            if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith("Bearer ")) {
                final String token = bearerToken.substring(7);
                final UUID id = UUID.fromString(token);
                final Account account = accountRepository.findById(id)
                        .orElseThrow(() -> new OrchidoraException("Account not found"));
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(account,
                        null, account.getAuthorities()));
            }
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException | OrchidoraException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            final OrchidoraErrorResponse errorResponse = OrchidoraErrorResponse.builder()
                    .target("token")
                    .message(e.getMessage())
                    .build();
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            response.getWriter().flush();
        }
    }
}
