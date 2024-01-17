package com.example.employeemanage.core.events.employee;

import lombok.Data;

@Data
public class EmployeeDeletedEvent {
    private String aggregateId;
    private Long employeeId;
}
