package spring.projectdemo.rest;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.swagger.v3.oas.annotations.Operation;
import spring.projectdemo.entities.Department;
import spring.projectdemo.entities.Employee;
import spring.projectdemo.entities.Job;
import spring.projectdemo.entities.JobHistory;
import spring.projectdemo.entities.JobHistory_Pk;
import spring.projectdemo.repo.DepartmentRepo;
import spring.projectdemo.repo.EmployeeRepo;
import spring.projectdemo.repo.JobHistoryRepo;
import spring.projectdemo.repo.JobRepo;

@CrossOrigin
@RestController
public class Controller {
	@Autowired
	private JobRepo jr;

	@Autowired
	private DepartmentRepo dr;

	@Autowired
	private EmployeeRepo er;

	@Autowired
	private JobHistoryRepo jhr;

	// 1 Get all jobs
	@GetMapping("/jobs")
	@Operation(summary = "Get all jobs", description = "Getting all the jobs")
	public List<Job> getJobs() {
		return jr.findAll();
	}

	@GetMapping("/jobHistory")
	public List<JobHistory> getJobHistory() {
		return jhr.findAll();
	}

	// 2 Get all departments
	@GetMapping("/departments")
	@Operation(summary = "Get all departments", description = "Getting all departments")
	public List<Department> getDepartments() {
		return dr.findAll();
	}

	// 3 Get employees by job id
	@GetMapping("/employee/{id}")
	@Operation(summary = "Get all employees by id", description = "Getting all employees by job id")
	public List<Employee> getEmployee(@PathVariable("id") int id) {
		return er.getEmployeeById(id);
	}

	// 3 Get employees by job id
	@GetMapping("/employee/job/{id}")
	@Operation(summary = "Get all employees by id", description = "Getting all employees by job id using query derivation")
	public List<Employee> getEmplById(@PathVariable("id") int id) {
		return er.findByJob_Id(id);
	}

	// 4 Get employees by department name
	@GetMapping("/employee/dept/{name}")
	@Operation(summary = "Get all employees by department", description = "Getting all employees by department name")
	public List<Employee> getEmplByName(@PathVariable("name") String name) {
		var v = er.findByDepartment_Dname(name);
		if (v.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Department not found");
		} else
			return v;
	}

	// 4 Get employees by department id
	@GetMapping("/employ/{id}")
	public List<Employee> getEmploId(@PathVariable("id") String id) {
		return er.findByDepartment_Did(id);
	}

	// 5 Get employees by name containing
	@GetMapping("/employ/name")
	@Operation(summary = "Get all employees by name containing", description = "Getting all employees by name containing given string")
	public List<Employee> getNameContaining(@RequestParam("s") String s) {
		var v = er.findByEnameContains(s);
		if (v.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No employee available");
		} else
			return v;
	}

	// 6 Get Employee having salary between given range
	@GetMapping("/employe/{min}/{max}")
	@Operation(summary = "Get employees by salary in range", description = "Getting employees by salary in guven range")
	public List<Employee> getEmployeeSalary(@PathVariable("min") double min, @PathVariable("max") double max) {
		var v = er.getEmployeeRange(min, max);
		if (v.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Out of Range");
		} else
			return v;
	}

	// 7 Get Employees By Experience
	@GetMapping("/getexperienceEmp/{year}")
	@Operation(summary = "Get employees by experience", description = "Getting Employees having Experience greater than given experience")
	public List<Employee> getEmpExperience(@PathVariable("year") int year) {
		var v = er.getAllEmployees(year);
		if (v.isEmpty())
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No employee greater than given experience");
		else
			return v;
	}

	// 8 Get jobhistory by employ name
	@GetMapping("/jobhis/{name}")
	@Operation(summary = "Get jobhistory of employee by given name", description = "Getting jobhistory of employee by given name")
	public JobHistory getJobHistoryEmploy(@PathVariable("name") String name) {
		var b = jhr.findByEmployee_Ename(name);
		if (b == null) {
			throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No employee with the given name");
		} else {
			return b;
		}
	}

	// 9 Updating Salary of an employee
	@PutMapping("/updateSalary")
	@Operation(summary = "Update salary of an employee for given employee id", description = "Updating salary of an employee for given employee id")
	public Employee setEmployeeSalary(@RequestParam("id") int id, @RequestParam("price") double price) {
		var employ = er.findById(id);
		if (employ.isPresent()) {
			var t = employ.get();
			t.setSalary(price);
			er.save(t);
			return t;
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found ");
	}

	// 10 Adding new row into JobHistory
	@PostMapping("/jobhistory/all")
	@Operation(summary = "Add new row into jobhistory", description = "Adding new row into JobHistory")
	public JobHistory addJobHistory(@RequestBody JobHistory jobs) {
		try {
			jhr.save(jobs);
			return jobs;
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data!");
		}
	}

	@GetMapping("/doj")
	public List<Employee> getEmpByDate(@RequestParam("date") String date) {
		return er.findByDoj(date);
	}

	@PostMapping("jobhistory")
	public JobHistory addJHistory(@RequestBody JobHistory job) {
		var a = jhr.findByIdAndEid(job.getId(), job.getEid());
		if (a != null) {
			throw new ResponseStatusException(HttpStatus.IM_USED, "Already details present");
		} else {
			try {
				job.getE_date();
				job.getS_date();
				jhr.save(job);
				return job;
			} catch (Exception e) {
				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Invalid Date format");
			}
		}
	}

	@DeleteMapping("emp/{id}/{eid}")
	public void deleteEmpDetails(@PathVariable("id") int id, @PathVariable("eid") int eid) {
		var b = er.findByIdAndEid(id, eid);
		var d = new JobHistory_Pk(id, eid);
		var c = jhr.findByIdAndEid(id, eid);
		if (c != null) {
			jhr.deleteById(d);
		}
		if (b != null) {
			er.deleteByIdAndEid(id, eid);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id not found");
		}
	}

	@DeleteMapping("jhis/{id}/{eid}")
	public void deleteJobHistory(@PathVariable("id") int id, @PathVariable("eid") int eid) {
		var b = new JobHistory_Pk(id, eid);
		var c = jhr.findByIdAndEid(id, eid);
		if (c != null) {
			jhr.deleteById(b);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Composite primary key not found");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id) {
		Optional<Employee> optionalEmployee = er.findById(id);
		if (optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();

			// Delete job history entries for the employee
			jhr.deleteByEmployee(employee);

			// Delete the employee
			er.delete(employee);

			return ResponseEntity.ok("Employee deleted successfully");
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
