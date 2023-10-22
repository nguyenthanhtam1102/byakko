package com.byakko.service.authentication.domain.domainapplication.utils;

import org.springframework.stereotype.Component;

@Component
public interface PasswordUtils {

    boolean matches(String rawPass, String encodePass);
    public String encode(String rawPass);

}
