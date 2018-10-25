package com.verizon.adb.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.adb.dao.EmployeeRepository;
import com.verizon.adb.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository empRepo;

	@Override
	public Employee getEmployeeById(long empId) {

		Employee emp = null;

		Optional<Employee> optEmp = empRepo.findById(empId);

		if (optEmp.isPresent()) {
			emp = optEmp.get();
		}
		return emp;
	}

	@Override
	public List<Employee> getAllEmployees() {
		return empRepo.findAll();
	}

	@Override
	public Employee addEmployees(Employee employee) {
		return empRepo.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return empRepo.save(employee);
	}

	@Override
	public boolean deleteEmployee(long empId) {
		boolean isDeleted = false;
		if (empRepo.existsById(empId)) {
			empRepo.deleteById(empId);
			isDeleted = true;
		}
		return isDeleted;
	}

	@Override
	public boolean existsByEmpMobile(String mobileNumber) {

		return empRepo.existsByEmpMobile(mobileNumber);
	}

	@Override
	public boolean existsByEmpEmail(String emailId) {

		return empRepo.existsByEmpEmail(emailId);
	}

	@Override
	public Employee findByEmpMobile(String empMobile) {
		return empRepo.findByEmpMobile(empMobile);
	}

	@Override
	public Employee findByEmpEmail(String empEmail) {
		return empRepo.findByEmpEmail(empEmail);
	}

	@Override
	public List<Employee> findAllByEmpDept(String empDept) {
		
		return empRepo.findAllByEmpDept(empDept);
	}

	@Override
	public List<Employee> findAllByDateOfBirth(LocalDate dateOfBirth) {
	
		return empRepo.findAllByDateOfBirth(dateOfBirth);
	}

}
