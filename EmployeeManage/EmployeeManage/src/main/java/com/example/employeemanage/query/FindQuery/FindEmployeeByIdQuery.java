package com.example.employeemanage.query.FindQuery;

import lombok.Data;

@Data
public class FindEmployeeByIdQuery {
    private Long employeeId;

    public FindEmployeeByIdQuery(Long employeeId) {
        this.employeeId = employeeId;
    }


}
