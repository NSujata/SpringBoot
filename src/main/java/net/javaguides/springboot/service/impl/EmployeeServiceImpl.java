package net.javaguides.springboot.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	//Types of Injection
	//1. Setter-based
	//2. Constructor-based
	//We use constructor based injection whenever we have a mandatory paramaters 
	// we use setter based injection if we have optional parameters
	private EmployeeRepository employeeRepository;
	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}
	
	//no need to add @Autowired annotation for this constructor
	//because whenever springboot finds Spring bean it has only one constructor
	//then springboot will automatically autowire this dependency
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		Optional <Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}else {
//			throw new ResourceNotFoundException("Employee", "Id", id);
//		}
		
		//instead of if-else, we can use lambda expression as well
		return employeeRepository.findById(id).orElseThrow(() -> 
			new ResourceNotFoundException("Employee", "Id",id ));
		
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		//check weather employee with given id exists or not in db
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id)); 
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		//save existing employee to DB
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		
		//check whether an employee exists in a DB or not
		employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id)); 

		employeeRepository.deleteById(id);
		
	}
	
	

}
