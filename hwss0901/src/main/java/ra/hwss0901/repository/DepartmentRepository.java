package ra.hwss0901.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ra.hwss0901.model.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
