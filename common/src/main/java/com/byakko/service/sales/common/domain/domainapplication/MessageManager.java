package com.byakko.service.sales.common.domain.domainapplication;

import org.springframework.stereotype.Component;

@Component
public interface MessageManager {

    String getMessage(String key);

}
