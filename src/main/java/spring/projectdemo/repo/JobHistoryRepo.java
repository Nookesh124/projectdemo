package spring.projectdemo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.projectdemo.entities.Employee;
import spring.projectdemo.entities.JobHistory;
import spring.projectdemo.entities.JobHistory_Pk;

public interface JobHistoryRepo extends JpaRepository<JobHistory, JobHistory_Pk>{
	JobHistory findByEmployee_Ename(String name);
	
	@Query(value="select * from jobhistory WHERE DATEDIFF(YEAR, sdate, edate)>:year",nativeQuery = true)
	List<JobHistory> getEmployeesByExperience(@Param("year") int year);
	
	@Query(value="select e.ename from jobhistory j join employee e on j.eid=e.eid WHERE DATEDIFF(YEAR, sdate, edate)>:year",nativeQuery = true)
	List<String> getAllEmployees(@Param("year") int year);
	
	JobHistory findByIdAndEid(int id,int eid);

	void deleteByEmployee(Employee employee);
}

