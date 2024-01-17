package com.example.employeemanage.core.events.employee;

import lombok.Data;

@Data
public class EmployeeCreatedEvent {
    private String aggregateId;
    private String firstName;
    private String lastName;
    private String dob;
    private String position;
    private Integer salary;
    private Integer leaveCount;
    private Integer lateCount;
}

