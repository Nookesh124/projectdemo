package spring.projectdemo.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="departments")
public class Department {
	@Id
	@Column(name ="did")
	private String did;
	
	@Column(name ="dname")
	private String dname;
	
	@Column(name ="eidhod")
	private int eidhod;
	
	@OneToMany(mappedBy = "department")
	@JsonIgnore
	private List<Employee> employees = new ArrayList<Employee>();

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public int getEidhod() {
		return eidhod;
	}

	public void setEidhod(int eidhod) {
		this.eidhod = eidhod;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public int hashCode() {
		return Objects.hash(did, dname, eidhod);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		return Objects.equals(did, other.did) && Objects.equals(dname, other.dname) && eidhod == other.eidhod;
	}

	@Override
	public String toString() {
		return "Department [did=" + did + ", dname=" + dname + ", eidhod=" + eidhod + "]";
	}
	
	
}
