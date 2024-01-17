package com.example.employeemanage.core.events.image;

import lombok.Data;

@Data
public class ImageUpdatedEvent {
    private String aggregateId;
    private String name;
    private String type;
    private byte[] imageData;
    private Long employeeId;

}
