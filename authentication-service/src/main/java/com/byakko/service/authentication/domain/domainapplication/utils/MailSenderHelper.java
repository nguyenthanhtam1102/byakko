package com.byakko.service.authentication.domain.domainapplication.utils;

import org.springframework.stereotype.Component;

@Component
public interface MailSenderHelper {

    void send(Mail mail);

}
