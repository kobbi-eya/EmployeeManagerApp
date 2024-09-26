package tech.getarrays.employeemanager.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.model.Department;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.repo.DepartmentRepository;
import tech.getarrays.employeemanager.repo.EmployeeRepo;
import java.util.Optional;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
    private final DepartmentRepository departmentRepo;
    private final EmployeeRepo employeeRepo;
    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo,DepartmentRepository departmentRepo ) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
    }
    public Employee addEmployee(Employee employee) {
        employee.setEmployeeCode(UUID.randomUUID().toString());

        // Vérifiez si le département existe par nom
        Department department = departmentRepo.findByName(employee.getDepartment().getName());
        if (department != null) { // Vérifiez si le département n'est pas null
            employee.setDepartment(department);
            return employeeRepo.save(employee);
        } else {
            throw new RuntimeException("Department not found with name: " + employee.getDepartment().getName());
        }
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }
    public Employee updateEmployee (Employee employee ) {
        return employeeRepo.save(employee);

    }

    public String getEmployeeRole(String email) {
        Employee employee = employeeRepo.findByEmail(email);
        return (employee != null) ? employee.getRole().name() : "ROLE_USER"; // Default role if not found
    }


    public Employee findEmployeeById(Long id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }



}
