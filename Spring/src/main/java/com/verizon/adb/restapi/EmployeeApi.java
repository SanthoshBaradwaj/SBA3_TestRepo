package com.verizon.adb.restapi;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.verizon.adb.model.Employee;
import com.verizon.adb.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping("/employees")
public class EmployeeApi {

	@Autowired
	public EmployeeService service;

	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {

		return new ResponseEntity<>(service.getAllEmployees(), HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long empId) {
		ResponseEntity<Employee> resp;

		Employee e = service.getEmployeeById(empId);
		if (e == null) {
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(e, HttpStatus.OK);
		}

		return resp;
	}

	@GetMapping("/{field}/{srhValue}")
	public ResponseEntity<List<Employee>> getAllEmployees(@PathVariable("field") String fieldBy,
			@PathVariable("srhValue") String searchValue) {

		ResponseEntity<List<Employee>> resp = null;

		switch (fieldBy) {
		case "empMobile":
			Employee eByMobNum = service.findByEmpMobile(searchValue);
			if (eByMobNum != null) {
				resp = new ResponseEntity<>(Collections.singletonList(eByMobNum), HttpStatus.OK);
			} else {
				resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
			break;
		case "empEmail":
			Employee eByEmail = service.findByEmpEmail(searchValue);
			if (eByEmail != null) {
				resp = new ResponseEntity<>(Collections.singletonList(eByEmail), HttpStatus.OK);
			} else {
				resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);

			}
			break;
		case "empDept":
			List<Employee> results = service.findAllByEmpDept(searchValue);
			if (results != null && results.size() != 0) {
				resp = new ResponseEntity<>(results, HttpStatus.OK);
			} else {
				resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			break;
		case "dob":
			List<Employee> results1 = service.findAllByDateOfBirth(LocalDate.parse(searchValue));
			if (results1 != null && results1.size() != 0) {
				resp = new ResponseEntity<>(results1, HttpStatus.OK);
			} else {
				resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			break;
		default:
			break;
		}
		return resp;
	}

	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		ResponseEntity<Employee> resp = null;

//		if (service.existsByEmpEmail(employee.getEmpEmail())) {
//			resp = new ResponseEntity<Employee>(HttpStatus.ALREADY_REPORTED);
//		}
//
//		if (service.existsByEmpMobile(employee.getEmpMobile())) {
//			resp = new ResponseEntity<Employee>(HttpStatus.ALREADY_REPORTED);
//
//		}

		if (resp == null) {
			Employee e = service.addEmployees(employee);

			if (e == null) {
				resp = new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
			} else {
				resp = new ResponseEntity<Employee>(e, HttpStatus.OK);
			}
		}
		return resp;

	}

	@PutMapping
	public ResponseEntity<Employee> updateContact(@RequestBody Employee employee) {
		ResponseEntity<Employee> resp = null;

		Employee e = service.getEmployeeById(employee.getEmpId());

//		if (!employee.getEmpEmail().equals(e.getEmpEmail())) {
//			if (service.existsByEmpEmail(employee.getEmpEmail())) {
//				resp = new ResponseEntity<Employee>(HttpStatus.ALREADY_REPORTED);
//			}
//		}
//
//		if (!employee.getEmpMobile().equals(e.getEmpMobile())) {
//			if (service.existsByEmpMobile(employee.getEmpMobile())) {
//				resp = new ResponseEntity<Employee>(HttpStatus.ALREADY_REPORTED);
//			}
//		}

		if (resp == null) {
			Employee e1 = service.updateEmployee(employee);

			if (e1 == null) {
				resp = new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
			} else {
				resp = new ResponseEntity<Employee>(e1, HttpStatus.OK);
			}
		}
		return resp;

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable("id") long empId) {
		ResponseEntity<Void> resp = null;

		if (service.deleteEmployee(empId)) {
			resp = new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			resp = new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return resp;
	}
}
