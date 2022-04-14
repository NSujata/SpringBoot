package net.javaguides.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Employee;

//Why not need to add @Repository annotation here? Ans: below
//JpaRespository internally provides @Repository annotation.
//For example it's implementation class is SimpleJpaRepository
//if we go inside SimpleJpaRepository, it has already annotated as @Repository
//Conclusion: SimpleJpaRepository provides @Reposity annoation internally. So no need to provide @Reposity annotation
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

}
