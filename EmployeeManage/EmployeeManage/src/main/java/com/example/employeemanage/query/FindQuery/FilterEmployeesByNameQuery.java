package com.example.employeemanage.query.FindQuery;

import lombok.Data;

@Data
public class FilterEmployeesByNameQuery {
    private  String firsName;

    public FilterEmployeesByNameQuery(String firsName) {
        this.firsName = firsName;
    }


}
