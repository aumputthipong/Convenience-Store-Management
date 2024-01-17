package com.example.employeemanage.query;

import com.example.employeemanage.core.data.Enitity.EmployeeEntity;
import com.example.employeemanage.core.data.Repository.EmployeeRepository;
import com.example.employeemanage.core.data.Repository.ImageRepository;
import com.example.employeemanage.query.FindQuery.*;
import com.example.employeemanage.query.rest.RestModel.EmployeeRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeesQueryHandler {
    private final EmployeeRepository employeeRepository;

    public EmployeesQueryHandler(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    @QueryHandler
    public List<EmployeeRestModel> findEmployees(FindAllEmployeeQuery query) {
        List<EmployeeRestModel> employeeRest = new ArrayList<>();
        List<EmployeeEntity> storedEmployees = employeeRepository.findAll();
        for (EmployeeEntity employeeEntity : storedEmployees) {
            EmployeeRestModel employeeRestModel = new EmployeeRestModel();
            BeanUtils.copyProperties(employeeEntity, employeeRestModel);
            employeeRest.add(employeeRestModel);
        }
        return employeeRest;
    }
    //findEmployeeById
    @QueryHandler
    public List<EmployeeRestModel> findEmployeeByName(FindEmployeeByIdQuery query) {
        List<EmployeeRestModel> employeeRest = new ArrayList<>();
        // ดึงข้อมูลพนักงานตาม ชื่อและสกุล จาก repository
        EmployeeEntity employeeEntity = employeeRepository.findByEmployeeId(query.getEmployeeId());
        if (employeeEntity != null) {
            EmployeeRestModel employeeRestModel = new EmployeeRestModel();
            BeanUtils.copyProperties(employeeEntity, employeeRestModel);
            employeeRest.add(employeeRestModel);
        }
        return employeeRest;
    }
    //filterFirstName
    @QueryHandler
    public List<EmployeeRestModel> findAllEmployeeByName(FilterEmployeesByNameQuery query) {
        List<EmployeeRestModel> employeeRest = new ArrayList<>();

        // ดึงข้อมูลพนักงานตามชื่อและสกุล จาก repository
        List<EmployeeEntity> employeeEntities = employeeRepository.findByFirstNameLike(query.getFirsName()+"%");

        // แปลงผลลัพธ์เป็น EmployeeRestModel
        for (EmployeeEntity employeeEntity : employeeEntities) {
            EmployeeRestModel employeeRestModel = new EmployeeRestModel();
            BeanUtils.copyProperties(employeeEntity, employeeRestModel);
            employeeRest.add(employeeRestModel);
        }
        return employeeRest;
    }

    //    filterLastName
    @QueryHandler
    public List<EmployeeRestModel> findAllEmployeeByLast(FilterEmployeesByLastQuery query) {
        List<EmployeeRestModel> employeeRest = new ArrayList<>();

        // ดึงข้อมูลพนักงานตามชื่อและสกุล จาก repository
        List<EmployeeEntity> employeeEntities = employeeRepository.findByLastNameLike(query.getLastName()+"%");

        // แปลงผลลัพธ์เป็น EmployeeRestModel
        for (EmployeeEntity employeeEntity : employeeEntities) {
            EmployeeRestModel employeeRestModel = new EmployeeRestModel();
            BeanUtils.copyProperties(employeeEntity, employeeRestModel);
            employeeRest.add(employeeRestModel);
        }
        return employeeRest;
    }

    @QueryHandler
    public List<EmployeeRestModel> findEmployeeBetweenSalary(FilterEmployeesBetweenSalaryQuery query) {
        List<EmployeeRestModel> employeeRest = new ArrayList<>();
        // ดึงข้อมูลพนักงานตามชื่อและสกุล จาก repository
        List<EmployeeEntity> employeeEntities = employeeRepository.findBySalaryBetween(query.getSalary1(),query.getSalary2());
        // แปลงผลลัพธ์เป็น EmployeeRestModel
        for (EmployeeEntity employeeEntity : employeeEntities) {
            EmployeeRestModel employeeRestModel = new EmployeeRestModel();
            BeanUtils.copyProperties(employeeEntity, employeeRestModel);
            employeeRest.add(employeeRestModel);
        }
        return employeeRest;
    }
    @QueryHandler
    public List<EmployeeRestModel> searchEmployeePosition(FilterEmployeeByPositionQuery query) {
        List<EmployeeRestModel> employeeRest = new ArrayList<>();
        // ดึงข้อมูลพนักงานตามชื่อและสกุล จาก repository
        List<EmployeeEntity> employeeEntities = employeeRepository.findByPositionLike(query.getPosition()+"%");
        // แปลงผลลัพธ์เป็น EmployeeRestModel
        for (EmployeeEntity employeeEntity : employeeEntities) {
            EmployeeRestModel employeeRestModel = new EmployeeRestModel();
            BeanUtils.copyProperties(employeeEntity, employeeRestModel);
            employeeRest.add(employeeRestModel);
        }
        return employeeRest;
    }
    @QueryHandler
    public List<EmployeeRestModel> filterEmployeeLeave(FilterEmployeeByLeaveQuery query) {
        List<EmployeeRestModel> employeeRest = new ArrayList<>();
        // ดึงข้อมูลพนักงานตามชื่อและสกุล จาก repository
        List<EmployeeEntity> employeeEntities = employeeRepository.findByLeaveCountBetween(query.getLeaveCount1(), query.getLeaveCount2());
        // แปลงผลลัพธ์เป็น EmployeeRestModel
        for (EmployeeEntity employeeEntity : employeeEntities) {
            EmployeeRestModel employeeRestModel = new EmployeeRestModel();
            BeanUtils.copyProperties(employeeEntity, employeeRestModel);
            employeeRest.add(employeeRestModel);
        }
        return employeeRest;
    }

    @QueryHandler
    public List<EmployeeRestModel> filterEmployeeLate(FilterEmployeeByLateQuery query) {
        List<EmployeeRestModel> employeeRest = new ArrayList<>();
        // ดึงข้อมูลพนักงานตามชื่อและสกุล จาก repository
        List<EmployeeEntity> employeeEntities = employeeRepository.findByLateCountBetween(query.getLateCount1(), query.getLateCount2());
        // แปลงผลลัพธ์เป็น EmployeeRestModel
        for (EmployeeEntity employeeEntity : employeeEntities) {
            EmployeeRestModel employeeRestModel = new EmployeeRestModel();
            BeanUtils.copyProperties(employeeEntity, employeeRestModel);
            employeeRest.add(employeeRestModel);
        }
        return employeeRest;
    }




}

