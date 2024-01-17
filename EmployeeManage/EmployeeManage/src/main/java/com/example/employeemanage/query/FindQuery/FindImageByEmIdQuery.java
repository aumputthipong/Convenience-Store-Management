package com.example.employeemanage.query.FindQuery;

import lombok.Data;

@Data
public class FindImageByEmIdQuery {
    private  String employeeId;


    public FindImageByEmIdQuery(String employeeId) {
        this.employeeId = employeeId;
    }
}
