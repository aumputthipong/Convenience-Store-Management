package com.example.employeemanage.core.events.employee;

import lombok.Data;

import java.util.Objects;

@Data
public class EmployeeUpdatedEvent {
    private String aggregateId;
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String dob;
    private String position;
    private Integer salary;
}
