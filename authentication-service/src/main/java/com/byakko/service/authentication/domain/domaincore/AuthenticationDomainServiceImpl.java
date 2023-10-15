package com.byakko.service.authentication.domain.domaincore;

import com.byakko.common.DomainConstants;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import com.byakko.service.authentication.domain.domaincore.entity.Employee;
import com.byakko.service.authentication.domain.domaincore.event.CustomerSignUpCompletedEvent;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class AuthenticationDomainServiceImpl implements AuthenticationDomainService {
    @Override
    public CustomerSignUpCompletedEvent validateAndInitializeCustomer(Customer customer) {
        customer.initialize();
        customer.validate();
        return new CustomerSignUpCompletedEvent(customer, ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
    }

    @Override
    public void validateAndInitializeEmployee(Employee employee) {
        employee.initialize();
        employee.validate();
    }
}
