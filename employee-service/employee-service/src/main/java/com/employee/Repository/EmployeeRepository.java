package com.employee.Repository;

import com.employee.domain.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeDetail,Long> {
}
