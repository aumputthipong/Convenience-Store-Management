package com.example.employeemanage.query.FindQuery;

import lombok.Data;

@Data
public class FilterEmployeesByLastQuery {
    private  String lastName;

    public FilterEmployeesByLastQuery(String lastName) {
        this.lastName = lastName;
    }
}
