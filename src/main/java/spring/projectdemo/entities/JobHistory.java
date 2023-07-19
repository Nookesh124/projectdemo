package spring.projectdemo.entities;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="jobhistory")
@IdClass(JobHistory_Pk.class)
public class JobHistory {
	@Id
	@Column(name ="id")
	private int id;
	
	@Id
	@Column(name ="eid")
	private int eid;
	
	@Column(name ="sdate")
	private String s_date;
	
	@Column(name ="edate")
	private String e_date;
	
	@ManyToOne
	@JoinColumn(name ="eid",insertable =false,updatable = false)
	@JsonIgnore
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name ="id",insertable = false,updatable = false)
	@JsonIgnore
	private Job job;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getS_date() {
		return s_date;
	}

	public void setS_date(String s_date) {
		this.s_date = s_date;
	}

	public String getE_date() {
		return e_date;
	}

	public void setE_date(String e_date) {
		this.e_date = e_date;
	}

	@Override
	public String toString() {
		return "JobHistory [s_date=" + s_date + ", e_date=" + e_date + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(e_date, s_date);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobHistory other = (JobHistory) obj;
		return Objects.equals(e_date, other.e_date) && Objects.equals(s_date, other.s_date);
	}
	
}
