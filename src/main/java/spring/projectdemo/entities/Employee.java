package spring.projectdemo.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="eid")
	private int eid;
	
	@Column(name ="ename")
	private String ename;
	
	@Column(name ="salary")
	private Double salary;
	
	@Column(name ="doj")
	private String doj;
	
	@Column(name ="id",insertable = false,updatable = false)
	private int id;
	
	@Column(name ="did",insertable = false,updatable = false)
	private String did;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch =FetchType.LAZY)
	@JoinColumn(name ="id")
	@JsonIgnore
	private Job job;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "did")
	@JsonIgnore
	private Department department;
	
	@OneToMany
	@JoinColumn(name="eid")
	@JsonIgnore
	private List<JobHistory> jobhis = new ArrayList<JobHistory>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public int getE_id() {
		return eid;
	}

	public void setE_id(int e_id) {
		this.eid = e_id;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<JobHistory> getJobhis() {
		return jobhis;
	}

	public void setJobhis(List<JobHistory> jobhis) {
		this.jobhis = jobhis;
	}

	@Override
	public int hashCode() {
		return Objects.hash(department, doj, eid, ename, job, jobhis, salary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(department, other.department) && Objects.equals(doj, other.doj) && eid == other.eid
				&& Objects.equals(ename, other.ename) && Objects.equals(job, other.job)
				&& Objects.equals(jobhis, other.jobhis) && Objects.equals(salary, other.salary);
	}

	@Override
	public String toString() {
		return "Employee [e_id=" + eid + ", ename=" + ename + ", salary=" + salary + ", doj=" + doj + ", job=" + job
				+ ", department=" + department + ", jobhis=" + jobhis + "]";
	}

}
