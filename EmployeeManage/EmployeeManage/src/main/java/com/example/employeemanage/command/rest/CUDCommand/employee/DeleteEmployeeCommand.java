package com.example.employeemanage.command.rest.CUDCommand.employee;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class DeleteEmployeeCommand {
    @TargetAggregateIdentifier
    private final String aggregateId;
    private final Long employeeId;
}
