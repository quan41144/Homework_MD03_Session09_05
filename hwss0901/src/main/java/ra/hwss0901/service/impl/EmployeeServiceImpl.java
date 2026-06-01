package ra.hwss0901.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ra.hwss0901.exception.DuplicateResourceException;
import ra.hwss0901.exception.ResourceNotFoundException;
import ra.hwss0901.model.dto.request.EmployeeCreateDTO;
import ra.hwss0901.model.entity.Department;
import ra.hwss0901.model.entity.Employee;
import ra.hwss0901.repository.DepartmentRepository;
import ra.hwss0901.repository.EmployeeRepository;
import ra.hwss0901.service.EmployeeService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final Cloudinary cloudinary;

    @Override
    public Employee createEmployee(EmployeeCreateDTO employeeCreateDTO) {
        if (employeeRepository.existsByEmail(employeeCreateDTO.getEmail())) {
            throw new DuplicateResourceException("Email đã được sử dụng");
        }
        Department department = departmentRepository.findById(employeeCreateDTO.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Phòng ban không tồn tại"));
        Employee employee = Employee.builder()
                .fullName(employeeCreateDTO.getFullName())
                .email(employeeCreateDTO.getEmail())
                .phone(employeeCreateDTO.getPhone())
                .salary(employeeCreateDTO.getSalary())
                .department(department)
                .build();
        return employeeRepository.save(employee);
    }

    @Override
    public Employee uploadAvatar(Long id, MultipartFile file) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên có ID " + id));
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("file không tồn tại!");
        }
        if (file.getSize() > 2 * 1024 * 1024) {
            throw new RuntimeException("Kích thước không được quá 2MB!");
        }
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null ||
                (!originalFilename.toLowerCase().endsWith(".png") && !originalFilename.toLowerCase().endsWith(".jpg") && !originalFilename.toLowerCase().endsWith(".jpeg"))
        ) {
            throw new RuntimeException("Không đúng định dạng file .jpg, .png, .jpeg");
        }
        String cloudinaryUrl = "";
        try {
            Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            cloudinaryUrl = uploadResult.get("secure_url").toString();
        }
        catch (Exception e) {
            throw new RuntimeException("Xảy ra lỗi: " + e.getMessage());
        }
        Employee employee1 = Employee.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .salary(employee.getSalary())
                .avatarUrl(cloudinaryUrl)
                .department(employee.getDepartment())
                .build();
        return employeeRepository.save(employee1);
    }
}
