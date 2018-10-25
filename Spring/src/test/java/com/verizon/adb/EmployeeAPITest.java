package com.verizon.adb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.verizon.adb.TestUtil.TestUtil;
import com.verizon.adb.model.Employee;
import com.verizon.adb.restapi.EmployeeApi;
import com.verizon.adb.service.EmployeeService;
import com.verizon.adb.model.Gender;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = EmployeeApi.class)
public class EmployeeAPITest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private EmployeeService empServiceMock;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void tearDown() {
		mockMvc = null;
	}

	@Test
	public void testGetAllEmployees() throws Exception {

		assertThat(this.empServiceMock).isNotNull();

		List<Employee> empList = new ArrayList<>();
		empList.add(new Employee());

		when(empServiceMock.getAllEmployees()).thenReturn(empList);

		mockMvc.perform(get("/employees")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testGetEmployeeById() throws Exception {
		assertThat(this.empServiceMock).isNotNull();
		int empId = 1;

		Employee empAdded = new Employee();

		empAdded.setEmpName("Santhosh");
		empAdded.setEmpBasic(35000);
		empAdded.setEmpEmail("santhosh.rockstar306@gmail.com");
		empAdded.setEmpMobile("9566041065");
		empAdded.setEmpDept("Development");
		empAdded.setDateOfBirth(LocalDate.of(1997, 06, 30));
		empAdded.setGender(Gender.MALE);

		when(empServiceMock.getEmployeeById(empId)).thenReturn(empAdded);

		mockMvc.perform(get("/employees/1")).andExpect(status().isOk()).andDo(print());

	}

	@Test
	public void testAddEmployees() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		Employee emp = new Employee();

//		emp.setEmpName("Jagadish");
//		emp.setEmpBasic(35000);
//		emp.setEmpEmail("jagadish@gmail.com");
//		emp.setEmpMobile("8856232107");
//		emp.setEmpDept("Development");
//		emp.setDateOfBirth(LocalDate.of(1996, 10, 22));
//		emp.setGender(Gender.MALE);

		Employee empAdded = new Employee();
//		empAdded.setEmpId(7);
//		empAdded.setEmpName("Jagadish");
//		empAdded.setEmpBasic(35000);
//		empAdded.setEmpEmail("jagadish@gmail.com");
//		empAdded.setEmpMobile("8856232107");
//		empAdded.setEmpDept("Development");
//		empAdded.setDateOfBirth(LocalDate.of(1996, 10, 22));
//		empAdded.setGender(Gender.MALE);

		when(empServiceMock.addEmployees(Mockito.any(Employee.class))).thenReturn(empAdded);

		mockMvc.perform(post("/employees").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(emp))).andDo(print()).andExpect(status().isOk());

	}

	@Test
	public void testUpdateEmployees() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		Employee emp = new Employee();
//		emp.setEmpId(7);
//		emp.setEmpName("Jagadish");
//		emp.setEmpBasic(35000);
//		emp.setEmpEmail("jagadish@gmail.com");
//		emp.setEmpMobile("8856232107");
//		emp.setEmpDept("Development");
//		emp.setDateOfBirth(LocalDate.of(1996, 10, 22));
//		emp.setGender(Gender.MALE);

		int empId = 7;

		Employee empAdded = new Employee();
//
//		emp.setEmpId(7);
//		emp.setEmpName("Jagadish");
//		emp.setEmpBasic(35000);
//		emp.setEmpEmail("jagadish@gmail.com");
//		emp.setEmpMobile("8856232107");
//		emp.setEmpDept("Development");
//		emp.setDateOfBirth(LocalDate.of(1996, 10, 22));
//		emp.setGender(Gender.MALE);

		when(empServiceMock.getEmployeeById(empId)).thenReturn(empAdded);

		when(empServiceMock.updateEmployee(Mockito.any(Employee.class))).thenReturn(emp);

		mockMvc.perform(put("/employees").contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(emp))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));

	}

	@Test
	public void testDeleteEmployee() throws Exception {
		assertThat(this.empServiceMock).isNotNull();

		int empId = 7;

		when(empServiceMock.deleteEmployee(empId)).thenReturn(true);

		mockMvc.perform(delete("/employees/7")).andExpect(status().isOk()).andDo(print());

	}

}
