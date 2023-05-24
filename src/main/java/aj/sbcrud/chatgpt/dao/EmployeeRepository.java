package aj.sbcrud.chatgpt.dao;

import aj.sbcrud.chatgpt.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
