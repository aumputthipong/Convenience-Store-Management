package com.example.employeemanage.command.rest.CUDCommand.image;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class CreateImageCommand {
    @TargetAggregateIdentifier
    private final String aggregateId;
    private final String name;
    private final String type;
    private final byte[] imageData;
    private final Long employeeId;
}
