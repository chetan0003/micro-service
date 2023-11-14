package com.employee.mapper;

import com.employee.domain.EmployeeDetail;
import com.employee.model.Employee;

import java.util.function.Function;

public interface EmployeeEntityMapper {

     Function<Employee, EmployeeDetail> employeeMapToEntity = employee ->
             EmployeeDetail.builder()
                     .name(employee.getName())
                     .city(employee.getCity())
                     .build();

     Function<EmployeeDetail, Employee> entityMapToEmployee = employee ->
             Employee.builder()
                     .id(employee.getId())
                     .name(employee.getName())
                     .city(employee.getCity())
                     .build();
}
