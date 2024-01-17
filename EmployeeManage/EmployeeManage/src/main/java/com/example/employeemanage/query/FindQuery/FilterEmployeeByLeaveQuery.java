package com.example.employeemanage.query.FindQuery;

import lombok.Data;

@Data
public class FilterEmployeeByLeaveQuery {
    private  Integer leaveCount1;
    private  Integer leaveCount2;
    public FilterEmployeeByLeaveQuery(Integer leaveCount1 ,Integer leaveCount2) {
        this.leaveCount1 = leaveCount1;
        this.leaveCount2 = leaveCount2;


    }
}
