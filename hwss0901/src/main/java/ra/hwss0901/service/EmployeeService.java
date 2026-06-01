package ra.hwss0901.service;

import org.springframework.web.multipart.MultipartFile;
import ra.hwss0901.model.dto.request.EmployeeCreateDTO;
import ra.hwss0901.model.entity.Employee;

public interface EmployeeService {
    Employee createEmployee(EmployeeCreateDTO employeeCreateDTO);
    Employee uploadAvatar(Long id, MultipartFile file);
}
