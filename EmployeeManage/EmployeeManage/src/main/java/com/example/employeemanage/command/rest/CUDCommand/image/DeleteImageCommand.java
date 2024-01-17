package com.example.employeemanage.command.rest.CUDCommand.image;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class DeleteImageCommand {
    @TargetAggregateIdentifier
    private final String aggregateId;
    private final Long employeeId;
}
