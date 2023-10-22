package com.byakko.service.authentication.domain.domainapplication.handler.systemadmin;

import com.byakko.common.DomainConstants;
import com.byakko.common.domain.exception.NotFoundException;
import com.byakko.service.authentication.application.security.JwtProvider;
import com.byakko.service.authentication.domain.domainapplication.dto.systemadmin.SystemAdminSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.systemadmin.SystemAdminSignInResponse;
import com.byakko.service.authentication.domain.domainapplication.utils.PasswordUtils;
import com.byakko.service.authentication.domain.domainapplication.SystemAdminManager;
import com.byakko.service.authentication.domain.domaincore.entity.SystemAdmin;
import com.byakko.service.authentication.domain.domaincore.exception.AuthenticationException;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class SystemAdminSignInCommandHandler {

    private final PasswordUtils passwordUtils;
    private final JwtProvider jwtProvider;

    public SystemAdminSignInCommandHandler(PasswordUtils passwordUtils, JwtProvider jwtProvider) {
        this.passwordUtils = passwordUtils;
        this.jwtProvider = jwtProvider;
    }

    public SystemAdminSignInResponse signIn(SystemAdminSignInCommand command) {
        SystemAdmin systemAdmin = SystemAdminManager.getInstance().findByUsername(command.getUsername())
                .orElseThrow(() -> new NotFoundException("username or password not correct"));

        if(!systemAdmin.getPassword().equals(command.getPassword()))
            throw new AuthenticationException("username or password not correct");

        return SystemAdminSignInResponse.Builder.builder()
                .idToken(jwtProvider.generateToken(systemAdmin))
                .refreshToken("")
                .expiresTime(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).plusMinutes(5).toEpochSecond())
                .userId(systemAdmin.getId().getValue())
                .build();
    }

}
