package com.byakko.service.authentication.domain.domainapplication.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailTemplate {

    @Value("${application.name}")
    private String APP_NAME;

    @Value("${frontend.host}")
    private String FRONTEND_HOST;

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
                        String.format(FRONTEND_HOST + "/verifyemailaddress?token=%s", token),
                        APP_NAME))
                .build();

    }
    public Mail getEmailAddressVerificationMailTemplateShopOwner(String mailTo, String displayName, String token) {
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
                        String.format(FRONTEND_HOST + "/ad/verifyemailaddress?token=%s", token),
                        APP_NAME))
                .build();

    }
    public Mail getPasswordResetMailTemplate(String mailTo, String token) {
        return Mail.Builder.builder()
                .to(mailTo)
                .subject(String.format("Reset your password for %s", APP_NAME))
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
                        String.format(FRONTEND_HOST + "/resetpassword?token=%s", token),
                        APP_NAME))
                .build();
    }
    public Mail getPasswordResetMailTemplateShopOwner(String mailTo, String token) {
        return Mail.Builder.builder()
                .to(mailTo)
                .subject(String.format("Reset your password for %s", APP_NAME))
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
                        String.format(FRONTEND_HOST + "/ad/resetpassword?token=%s", token),
                        APP_NAME))
                .build();
    }
}
