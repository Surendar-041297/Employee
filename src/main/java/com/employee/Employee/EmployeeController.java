package com.employee.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class EmployeeController {

	 @Autowired
	 EmployeeRepository EmployeeRepository;
	 
	 @GetMapping("/Employees")
	 public List<Employee> getEmployees()
	 {
		 List<Employee> employeeList = EmployeeRepository.findAll();
		return employeeList;
		 
	 }
	 @GetMapping("/Employees/{id}")
	  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long id) {
	    Optional<Employee> EmployeeData = EmployeeRepository.findById(id);

	    if (EmployeeData.isPresent()) {
	      return new ResponseEntity<>(EmployeeData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 @PostMapping("/Employees")
	  public ResponseEntity<Employee> createEmployee(@RequestBody Employee Employee) {
	    try {
	       EmployeeRepository.save(new Employee(Employee.getName(), Employee.getDesignation(), Employee.getSalary()));
	      return new ResponseEntity<>( HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	 
	 @PutMapping("/Employees/{id}")
	  public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee Employee) {
	    Optional<Employee> EmployeeData = EmployeeRepository.findById(id);

	    if (EmployeeData.isPresent()) {
	      Employee _Employee = EmployeeData.get();
	      _Employee.setName(Employee.getName());
	      _Employee.setDesignation(Employee.getDesignation());
	      _Employee.setSalary(Employee.getSalary());
	      return new ResponseEntity<>(EmployeeRepository.save(_Employee), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	 
	 @DeleteMapping("/Employees/{id}")
	  public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
	    try {
	      EmployeeRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
}
