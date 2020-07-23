package demo.example.springbootcrud.controller;

import demo.example.springbootcrud.exception.RecordNotFoundException;
import demo.example.springbootcrud.model.EmployeeEntity;
import demo.example.springbootcrud.model.EmployeeView;
import demo.example.springbootcrud.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeServiceImpl service;

    public EmployeeController(EmployeeServiceImpl service) {
        this.service = service;
    }

    @GetMapping
    public List<EmployeeView> getAllEmployees() throws RecordNotFoundException {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeEntity getEmployeeById(@PathVariable Long id) throws RecordNotFoundException {
        return service.getEmployeeById(id);
    }

    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeEntity newEmployee) {
        return service.createEmployee(newEmployee);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity updateEntity) {
        service.updateEmployee(id, updateEntity);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable Long id) throws RecordNotFoundException {
        service.deleteEmployeeById(id);
    }

}
