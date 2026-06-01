package ra.hwss0901.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.hwss0901.model.dto.request.DepartmentDTO;
import ra.hwss0901.model.entity.Department;
import ra.hwss0901.repository.DepartmentRepository;
import ra.hwss0901.service.DepartmentService;
@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(DepartmentDTO departmentDTO) {
        Department department = Department.builder()
                .name(departmentDTO.getName())
                .description(departmentDTO.getDescription())
                .build();
        return departmentRepository.save(department);
    }
}
