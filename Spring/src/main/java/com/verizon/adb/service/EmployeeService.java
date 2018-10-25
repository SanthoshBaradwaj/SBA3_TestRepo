package com.verizon.adb.service;

import java.time.LocalDate;
import java.util.List;

import com.verizon.adb.model.Employee;

public interface EmployeeService {

	Employee getEmployeeById(long empId);

	List<Employee> getAllEmployees();

	Employee addEmployees(Employee employee);

	Employee updateEmployee(Employee employee);

	boolean deleteEmployee(long empId);

	boolean existsByEmpMobile(String mobileNumber);

	boolean existsByEmpEmail(String emailId);

	Employee findByEmpMobile(String empMobile);

	Employee findByEmpEmail(String empEmail);

	List<Employee> findAllByEmpDept(String empDept);

	List<Employee> findAllByDateOfBirth(LocalDate dateOfBirth);

}
