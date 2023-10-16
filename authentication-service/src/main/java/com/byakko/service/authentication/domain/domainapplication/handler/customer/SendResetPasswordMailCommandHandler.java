package com.byakko.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.SendResetPasswordMailCommand;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domainapplication.utils.MailSenderHelper;
import com.byakko.service.authentication.domain.domainapplication.utils.MailTemplate;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class SendResetPasswordMailCommandHandler {

    private final CustomerCommandHandlerHelper customerCommandHandlerHelper;
    private final CustomerRepository customerRepository;
    private final MailSenderHelper mailSenderHelper;

    public SendResetPasswordMailCommandHandler(CustomerCommandHandlerHelper customerCommandHandlerHelper,
                                               CustomerRepository customerRepository,
                                               MailSenderHelper mailSenderHelper) {
        this.customerCommandHandlerHelper = customerCommandHandlerHelper;
        this.customerRepository = customerRepository;
        this.mailSenderHelper = mailSenderHelper;
    }

    public void handler(SendResetPasswordMailCommand command) {
        Customer customer = customerCommandHandlerHelper.findCustomerByPhoneOrEmail(command.getPhoneOrEmail(), command.getPhoneOrEmail());

        mailSenderHelper.send(MailTemplate.getPasswordResetMailTemplate(customer.getEmail()));
    }

}
