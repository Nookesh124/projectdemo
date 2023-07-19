package spring.projectdemo.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;

public class JobHistory_Pk implements Serializable{
	
	@Column(name ="eid")
	private int eid;
	
	@Column(name="id")
	private int id;

	public JobHistory_Pk() {
		
	}
	
	public JobHistory_Pk(int id,int e_id) {
		this.eid = e_id;
		this.id = id;
	}

	public int getE_id() {
		return eid;
	}

	public void setE_id(int e_id) {
		this.eid = e_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(eid, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobHistory_Pk other = (JobHistory_Pk) obj;
		return eid == other.eid && id == other.id;
	}

	@Override
	public String toString() {
		return "JobHistory_Pk [e_id=" + eid + ", id=" + id + "]";
	}
}
