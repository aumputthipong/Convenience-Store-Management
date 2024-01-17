package com.example.employeemanage.query.FindQuery;

import lombok.Data;

@Data
public class FilterEmployeeByPositionQuery {
    private  String position;


    public FilterEmployeeByPositionQuery(String position) {
        this.position = position;
    }
}
