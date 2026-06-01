package ra.hwss0901.service;

import ra.hwss0901.model.dto.request.DepartmentDTO;
import ra.hwss0901.model.entity.Department;

public interface DepartmentService {
    Department createDepartment(DepartmentDTO departmentDTO);
}
