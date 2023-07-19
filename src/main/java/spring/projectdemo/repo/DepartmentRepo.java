package spring.projectdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.projectdemo.entities.Department;

public interface DepartmentRepo extends JpaRepository<Department, String> {

}
