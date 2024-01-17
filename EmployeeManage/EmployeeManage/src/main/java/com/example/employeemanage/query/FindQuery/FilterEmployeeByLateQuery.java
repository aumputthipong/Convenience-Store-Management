package com.example.employeemanage.query.FindQuery;

import lombok.Data;

@Data
public class FilterEmployeeByLateQuery {
    private  Integer lateCount1;
    private  Integer lateCount2;
    public FilterEmployeeByLateQuery(Integer lateCount1 ,Integer lateCount2) {
        this.lateCount1 = lateCount1;
        this.lateCount2 = lateCount2;
    }
    }
