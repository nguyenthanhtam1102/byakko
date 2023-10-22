package com.byakko.service.authentication.domain.domainapplication;

import com.byakko.common.DomainConstants;
import com.byakko.service.authentication.domain.domaincore.entity.SystemAdmin;
import com.byakko.service.authentication.domain.domaincore.valueobject.UserId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class SystemAdminManager {

    private static SystemAdminManager instance = new SystemAdminManager();

    public static SystemAdminManager getInstance() {
        return instance;
    }

    private final Set<SystemAdmin> admins;

    public SystemAdminManager() {
        this.admins = new HashSet<>();
        this.admins.add(new SystemAdmin(new UserId(String.valueOf(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).toEpochSecond())), "admin", "admin"));
    }

    public Optional<SystemAdmin> findByUsername(String username) {
        return admins.stream().filter(admin -> admin.getUsername().equals(username)).findFirst();
    }

}
