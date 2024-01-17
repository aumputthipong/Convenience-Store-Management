package com.example.employeemanage.core.events.employee;

import lombok.Data;

@Data
public class LeaveCountUpdatedEvent {
    private String aggregateId;
    private Long employeeId;
    private Integer leaveCount;
}
