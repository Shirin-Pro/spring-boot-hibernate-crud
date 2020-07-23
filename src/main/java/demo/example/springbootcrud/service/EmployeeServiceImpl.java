package demo.example.springbootcrud.service;

import demo.example.springbootcrud.exception.RecordNotFoundException;
import demo.example.springbootcrud.model.EmployeeEntity;
import demo.example.springbootcrud.model.EmployeeView;
import demo.example.springbootcrud.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<EmployeeView> getAllEmployees() throws RecordNotFoundException {
        List<EmployeeEntity> employeeList = repository.findAll();
        if (employeeList.size() > 0) {
            return employeeList.stream()
                    .map(e -> new EmployeeView(e.getId(), e.getFirstName(), e.getLastName()))
                    .collect(Collectors.toList());
        } else {
            throw new RecordNotFoundException("No employee record exists for given id");
        }
    }

    public EmployeeEntity getEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> singleEmployee = repository.findById(id);
        if (singleEmployee.isPresent()) {
            return singleEmployee.get();
        } else {
            throw new RecordNotFoundException("No employee record exists for given id");
        }
    }

    public EmployeeEntity createEmployee(EmployeeEntity newEntity) {

        return repository.save(newEntity);
    }

    public void updateEmployee(Long id, EmployeeEntity entity) {
        Optional<EmployeeEntity> currentEmployee = repository.findById(id);
        if (currentEmployee.isPresent()) {
            EmployeeEntity updateEntity = currentEmployee.get();
            updateEntity.setFirstName(entity.getFirstName());
            updateEntity.setLastName(entity.getLastName());
            updateEntity.setEmail(entity.getEmail());
            repository.save(updateEntity);
        }
    }

    public void deleteEmployeeById(Long id) throws RecordNotFoundException {
        Optional<EmployeeEntity> currentEmployee = repository.findById(id);
        if (currentEmployee.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exists for given id");
        }
    }


}
