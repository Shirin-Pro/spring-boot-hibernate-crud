package demo.example.springbootcrud.service;

import demo.example.springbootcrud.model.EmployeeEntity;
import demo.example.springbootcrud.model.EmployeeView;

import java.util.List;

public interface EmployeeService {

    List<EmployeeView> getAllEmployees();

    EmployeeEntity getEmployeeById(Long id);

    EmployeeEntity createEmployee(EmployeeEntity newEntity);

    void updateEmployee(Long id, EmployeeEntity entity);

    void deleteEmployeeById(Long id);
}
