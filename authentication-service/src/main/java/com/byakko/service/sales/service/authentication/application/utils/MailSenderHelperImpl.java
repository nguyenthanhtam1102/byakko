package com.byakko.service.sales.service.authentication.application.utils;

import com.byakko.service.sales.service.authentication.domain.domainapplication.utils.Mail;
import com.byakko.service.sales.service.authentication.domain.domainapplication.utils.MailSenderHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSenderHelperImpl implements MailSenderHelper {

    private final JavaMailSender emailSender;

    @Override
    public void send(Mail mail) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mail.getTo());
            message.setSubject(mail.getSubject());
            message.setText(mail.getBody());
            emailSender.send(message);
        } catch (MailParseException ex) {
            // Không chuyển đổi parse được nội dung mail message

        } catch (MailAuthenticationException ex) {
            // Lỗi xác thực tài khoản gửi mail

        } catch (MailSendException ex) {
            // Lỗi gửi mail không thành công
        } catch (MailException ex) {
            // Các lỗi khác
        }
    }

}
