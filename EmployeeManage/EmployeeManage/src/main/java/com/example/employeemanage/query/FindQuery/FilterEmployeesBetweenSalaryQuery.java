package com.example.employeemanage.query.FindQuery;

import lombok.Data;

@Data
public class FilterEmployeesBetweenSalaryQuery {
    private  Integer salary1;
    private  Integer salary2;

    public FilterEmployeesBetweenSalaryQuery(Integer salary1, Integer salary2) {
        this.salary1 = salary1;
        this.salary2 = salary2;
    }
}
