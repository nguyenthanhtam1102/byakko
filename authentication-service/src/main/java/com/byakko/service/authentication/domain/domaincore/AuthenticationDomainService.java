package com.byakko.service.authentication.domain.domaincore;

import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import com.byakko.service.authentication.domain.domaincore.entity.Employee;
import com.byakko.service.authentication.domain.domaincore.entity.Role;
import com.byakko.service.authentication.domain.domaincore.event.CustomerSignUpCompletedEvent;
import org.springframework.stereotype.Component;

@Component
public interface AuthenticationDomainService {

    CustomerSignUpCompletedEvent validateAndInitializeCustomer(Customer customer);
    void validateAndInitializeEmployee(Employee employee);

}
