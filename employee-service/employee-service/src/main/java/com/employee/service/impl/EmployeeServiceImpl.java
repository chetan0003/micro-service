package com.employee.service.impl;

import com.employee.Repository.EmployeeRepository;
import com.employee.domain.EmployeeDetail;
import com.employee.feign.client.DepartmentClient;
import com.employee.mapper.EmployeeEntityMapper;
import com.employee.model.Department;
import com.employee.model.Employee;
import com.employee.model.Response;
import com.employee.service.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {


    private final EmployeeRepository employeeRepository;
    private final DepartmentClient departmentClient;
    private ObjectMapper mapper = new ObjectMapper();
    public static final String DEPARTMENT_SERVICE = "departmentService";
    @Override
    public Response createEmployee(Employee employee) {
        Response response = null;
        EmployeeDetail entity = EmployeeEntityMapper.employeeMapToEntity.apply(employee);
        EmployeeDetail save = employeeRepository.save(entity);
        employee.getDepartment().setEmployeeId(save.getId());
        Employee emp = EmployeeEntityMapper.entityMapToEmployee.apply(save);

        if (employee.getDepartment() != null)
            response = departmentClient.postDepartment(employee.getDepartment());

        if (response != null && response.getStatusCode().equals(200)) {
            Department department = mapper.convertValue(response.getData(), Department.class);
            emp.setDepartment(department);
        }
        return Response.builder().statusCode(200).data(emp).build();
    }

    @Override
    @CircuitBreaker(name = DEPARTMENT_SERVICE,fallbackMethod = "getAllEmployee")
    //@Retry(name = DEPARTMENT_SERVICE,fallbackMethod = "getAllEmployee")
    @RateLimiter(name = DEPARTMENT_SERVICE,fallbackMethod = "getAllEmployee")
    public Response getDepartmentByEmployeeId(Long employeeId) {
        return departmentClient.getDepartmentByEmployeeId(employeeId);
    }

    public Response getAllEmployee(Long employeeId ,Exception e) {
        log.error("Department service down " + e.getMessage());
        Employee employee = employeeRepository.findAll().stream().filter(f -> f.getId().equals(employeeId))
                .map(m -> EmployeeEntityMapper.entityMapToEmployee.apply(m)).findFirst().get();
        Employee build = Employee.builder().id(1L).name("fake employee").city("nagpur").build();
        return  Response.builder().statusCode(200).data(build).build();
    }

}
