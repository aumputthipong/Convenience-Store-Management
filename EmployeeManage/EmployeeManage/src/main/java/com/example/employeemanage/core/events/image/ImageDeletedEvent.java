package com.example.employeemanage.core.events.image;

import lombok.Data;

@Data
public class ImageDeletedEvent {
    private String aggregateId;
    private Long employeeId;

}
