package com.byakko.service.authentication.domain.domainapplication.handler.customer;

import com.byakko.service.authentication.domain.domainapplication.dto.customer.ResendEmailAddressVerificationMailCommand;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domainapplication.utils.MailSenderHelper;
import com.byakko.service.authentication.domain.domainapplication.utils.MailTemplate;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class ResendEmailAddressVerificationMailCommandHandler {

    private final CustomerCommandHandlerHelper customerCommandHandlerHelper;
    private final CustomerRepository customerRepository;
    private final MailSenderHelper mailSenderHelper;

    public ResendEmailAddressVerificationMailCommandHandler(CustomerCommandHandlerHelper customerCommandHandlerHelper,
                                                            CustomerRepository customerRepository,
                                                            MailSenderHelper mailSenderHelper) {
        this.customerCommandHandlerHelper = customerCommandHandlerHelper;
        this.customerRepository = customerRepository;
        this.mailSenderHelper = mailSenderHelper;
    }

    public void resend(ResendEmailAddressVerificationMailCommand command) {
        Customer customer = customerCommandHandlerHelper.findCustomerById(command.getId());

        mailSenderHelper.send(MailTemplate.getEmailAddressVerificationMailTemplate(customer.getEmail()));

        // Cần lưu lại danh sách yêu cầu trong một khooản thời gian để tránh người dùng nhấn liên tục
    }

}
