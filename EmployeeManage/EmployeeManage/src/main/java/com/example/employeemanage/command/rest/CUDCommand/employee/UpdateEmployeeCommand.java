package com.example.employeemanage.command.rest.CUDCommand.employee;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class UpdateEmployeeCommand {

    @TargetAggregateIdentifier
    private final String aggregateId;
    private final Long employeeId;
    private final String firstName;
    private final String lastName;
    private final String dob;
    private final String position;
    private final Integer salary;

}
