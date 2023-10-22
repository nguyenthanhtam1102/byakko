package com.byakko.service.authentication.domain.domainapplication.dto.employee;

public class EmployeeSignInCommand {

    private String employeeId;
    private String password;

    public String getEmployeeId() {
        return employeeId;
    }

    public String getPassword() {
        return password;
    }
}
