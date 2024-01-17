package com.example.employeemanage.core.events.employee;

import lombok.Data;

@Data
public class LateCountUpdatedEvent {
    private String aggregateId;
    private Long employeeId;
    private Integer lateCount;
}
