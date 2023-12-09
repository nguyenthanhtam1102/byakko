package com.byakko.service.sales.service.authentication.application.utils;

import com.byakko.service.sales.service.authentication.domain.domainapplication.utils.PasswordUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtilsImpl implements PasswordUtils {

    private final PasswordEncoder passwordEncoder;

    public PasswordUtilsImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean matches(String rawPass, String encodePass) {
        return passwordEncoder.matches(rawPass, encodePass);
    }

    @Override
    public String encode(String rawPass) {
        return passwordEncoder.encode(rawPass);
    }

}
