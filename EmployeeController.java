package com.sb.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.exception.ResourceNotFoundException;
import com.sb.model.Employee1;
import com.sb.repository.EmployeeRepository;


@RestController
@RequestMapping("/empapi")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List < Employee1 > getAllEmployees() {
        return employeeRepository.findAll();
    }
employee.setEmailId(employeeDetails.getEmailId());
    /*@GetMapping("/employees/{id}")
    public ResponseEntity < Employee1 > getEmployeeById
    (@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        Employee1 employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Your Entered Employee Number is not available in Database,Could you please try with other Emp Number :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }
*/    
    @GetMapping("/employees/{id}")
    public ResponseEntity < Employee1 > getEmployeeById
    (@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        Employee1 employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Your Entered Employee Number is not available in Database,Could you please try with other Emp Number :: " + employeeId));
        return ResponseEntity.ok().body(employee);
        
         
    }

    @PostMapping("/employ")
    public Employee1 createEmployee(@Valid @RequestBody Employee1 employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity < Employee1 > updateEmployee(@PathVariable(value = "id") Long employeeId,
        @Valid @RequestBody Employee1 employeeDetails) throws ResourceNotFoundException {
        Employee1 employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for Update this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
		/*RQ_1361-Code changes - Start*/		
		employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
		/*RQ_1361-Code changes - End*/
        final Employee1 updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    

    @DeleteMapping("/employees/{id}")
    public Map < String, Boolean > deleteEmployee(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        Employee1 employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

employee.setEmailId(employeeDetails.getEmailId());
        employeeRepository.delete(employee);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
