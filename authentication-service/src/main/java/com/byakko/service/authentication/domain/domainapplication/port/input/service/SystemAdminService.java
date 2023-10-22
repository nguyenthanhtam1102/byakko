package com.byakko.service.authentication.domain.domainapplication.port.input.service;

import com.byakko.service.authentication.domain.domainapplication.dto.systemadmin.SystemAdminSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.systemadmin.SystemAdminSignInResponse;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Component
@Validated
public interface SystemAdminService {

    SystemAdminSignInResponse signIn(@Valid SystemAdminSignInCommand command);

}
