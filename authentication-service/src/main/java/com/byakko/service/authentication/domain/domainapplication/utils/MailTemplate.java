package com.byakko.service.authentication.domain.domainapplication.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailTemplate {

    @Value("${application.name}")
    private String APP_NAME;

    public Mail getEmailAddressVerificationMailTemplate(String mailTo, String displayName, String token) {
        return Mail.Builder.builder()
                .to(mailTo)
                .subject(String.format("Verify your email for %s", APP_NAME))
                .body(String.format("Hello %s,\n" +
                        "\n" +
                        "Follow this link to verify your email address.\n" +
                        "\n" +
                        "%s\n" +
                        "\n" +
                        "If you didn’t ask to verify this address, you can ignore this email.\n" +
                        "\n" +
                        "Thanks,\n" +
                        "\n" +
                        "Your %s team",
                        displayName,
                        String.format("http://localhost:3000/verifyemailaddress?token=%s", token),
                        APP_NAME))
                .build();
    }

    public Mail getPasswordResetMailTemplate(String mailTo, String token) {
        return Mail.Builder.builder()
                .to(mailTo)
                .subject(String.format("Reset your password for %s", "APP NAME"))
                .body(String.format("Hello,\n" +
                        "\n" +
                        "Follow this link to reset your %s password for your %s account.\n" +
                        "\n" +
                        "%s\n" +
                        "\n" +
                        "If you didn’t ask to reset your password, you can ignore this email.\n" +
                        "\n" +
                        "Thanks,\n" +
                        "\n" +
                        "Your %s team",
                        APP_NAME,
                        mailTo,
                        String.format("http://localhost:3000/resetpassword?token=%s", token),
                        APP_NAME))
                .build();
    }

}
