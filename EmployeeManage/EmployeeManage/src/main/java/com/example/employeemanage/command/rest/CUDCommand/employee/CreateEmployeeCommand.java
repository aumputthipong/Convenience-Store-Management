package com.example.employeemanage.command.rest.CUDCommand.employee;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class CreateEmployeeCommand {
    @TargetAggregateIdentifier
    private final String aggregateId;
    private final String firstName;
    private final String lastName;
    private final String dob;
    private final String position;
    private final Integer salary;
    private final Integer leaveCount;
    private final Integer lateCount;

}
