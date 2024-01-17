package com.example.employeemanage.query.rest.RestModel;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class ImageRestModel {
    private long id;
    private String name;
    private String type;
    private byte[] imageData;
    private Long employeeId;
}
