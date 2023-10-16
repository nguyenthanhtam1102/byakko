package com.byakko.common.utils;

import com.byakko.service.authentication.domain.domaincore.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface JwtUtils {

    void validateToken(String token);
    JwtPayload getTokenPayload(String token);

}
