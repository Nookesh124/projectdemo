package spring.projectdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.projectdemo.entities.Job;

public interface JobRepo extends JpaRepository<Job, Integer> {
{
}
}
