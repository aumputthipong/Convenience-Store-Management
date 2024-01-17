package com.example.employeemanage.core.data.Repository;

import com.example.employeemanage.core.data.Enitity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

//    EmployeeEntity findByEmployeeId(String employeeId);
EmployeeEntity findByEmployeeId(Long employeeId);
    EmployeeEntity findByEmployeeIdOrFirstName(Long employeeId,String firstName);
    List<EmployeeEntity> findByFirstNameLike(String firstName);
    List<EmployeeEntity> findByLastNameLike(String lastName);
    EmployeeEntity findByFirstNameAndLastName(String firstName,String lastName);
    EmployeeEntity findByFirstName(String firstname);
    void deleteByFirstNameAndLastName(String firstName,String lastName);
    void deleteByEmployeeId(Long employeeId);
    List<EmployeeEntity> findBySalaryBetween(Integer salary1, Integer salary2);

    List<EmployeeEntity> findByPositionLike(String position);

    List<EmployeeEntity> findByLeaveCountBetween(Integer leaveCount1, Integer leaveCount2);
    List<EmployeeEntity> findByLateCountBetween(Integer lateCount1, Integer lateCount2);


}
