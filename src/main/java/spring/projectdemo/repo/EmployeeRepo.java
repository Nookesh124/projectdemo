package spring.projectdemo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.projectdemo.entities.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {

	@Query("From Employee where job.id in :id")
	List<Employee> getEmployeeById(@Param("id") int id);
	
	//QueryDerivation
	List<Employee> findByJob_Id(int id);
	
	//QueryDerivation
	List<Employee> findByDepartment_Dname(String name);
	
	//QueryDerivation
	List<Employee> findByDepartment_Did(String id);
	
	List<Employee> findByEnameContains(String s);
	
	@Query("from Employee where salary Between :min and :max")
	List<Employee> getEmployeeRange(@Param("min") double min,@Param("max") double max);
	
	
	@Query(value="select * from employee where datediff(yy, doj, getDate())>:years",nativeQuery = true)
	List<Employee> getAllEmployees(@Param("years") int year);
	
	List<Employee> findByDoj(String doj);
	
	Employee findByIdAndEid(int id,int eid);
	
	void deleteByIdAndEid(int id,int eid);
	
}
