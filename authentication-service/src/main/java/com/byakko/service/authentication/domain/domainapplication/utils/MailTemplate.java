package com.byakko.service.authentication.domain.domainapplication.utils;

public class MailTemplate {

    public static Mail getEmailAddressVerificationMailTemplate(String mailTo) {
        return Mail.Builder.builder()
                .from("")
                .to("")
                .subject("Verify your email for %APP_NAME%")
                .body("Hello %DISPLAY_NAME%,\n" +
                        "\n" +
                        "Follow this link to verify your email address.\n" +
                        "\n" +
                        "https://commerce-6a53b.firebaseapp.com/__/auth/action?mode=action&oobCode=code\n" +
                        "\n" +
                        "If you didn’t ask to verify this address, you can ignore this email.\n" +
                        "\n" +
                        "Thanks,\n" +
                        "\n" +
                        "Your %APP_NAME% team")
                .build();
    }

    public static Mail getPasswordResetMailTemplate(String mailTo) {
        return Mail.Builder.builder()
                .from("")
                .to("")
                .subject("Reset your password for %APP_NAME%")
                .body("Hello,\n" +
                        "\n" +
                        "Follow this link to reset your %APP_NAME% password for your %EMAIL% account.\n" +
                        "\n" +
                        "https://commerce-6a53b.firebaseapp.com/__/auth/action?mode=action&oobCode=code\n" +
                        "\n" +
                        "If you didn’t ask to reset your password, you can ignore this email.\n" +
                        "\n" +
                        "Thanks,\n" +
                        "\n" +
                        "Your %APP_NAME% team")
                .build();
    }

    public static Mail getVerificationMailTemplate(String mailTo) {
        return Mail.Builder.builder()
                .from("")
                .to("")
                .subject("Verification code for %APP_NAME%")
                .body("%LOGIN_CODE% is your verification code for %APP_NAME%.")
                .build();
    }

}
