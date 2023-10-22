package com.byakko.service.authentication.domain.domainapplication.port.input.service.impl;

import com.byakko.service.authentication.domain.domainapplication.dto.systemadmin.SystemAdminSignInCommand;
import com.byakko.service.authentication.domain.domainapplication.dto.systemadmin.SystemAdminSignInResponse;
import com.byakko.service.authentication.domain.domainapplication.handler.systemadmin.SystemAdminSignInCommandHandler;
import com.byakko.service.authentication.domain.domainapplication.port.input.service.SystemAdminService;
import org.springframework.stereotype.Component;

@Component
public class SystemAdminServiceImpl implements SystemAdminService {

    private final SystemAdminSignInCommandHandler systemAdminSignInCommandHandler;

    public SystemAdminServiceImpl(SystemAdminSignInCommandHandler systemAdminSignInCommandHandler) {
        this.systemAdminSignInCommandHandler = systemAdminSignInCommandHandler;
    }

    @Override
    public SystemAdminSignInResponse signIn(SystemAdminSignInCommand command) {
        return systemAdminSignInCommandHandler.signIn(command);
    }
}
