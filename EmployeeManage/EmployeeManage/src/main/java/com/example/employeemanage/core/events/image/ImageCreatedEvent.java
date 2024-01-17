package com.example.employeemanage.core.events.image;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class ImageCreatedEvent {
    private String aggregateId;
    private String name;
    private String type;
    private byte[] imageData;
    private Long employeeId;



}
