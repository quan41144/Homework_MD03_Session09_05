package ra.hwss0901.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.hwss0901.model.dto.request.AvatarUploadDTO;
import ra.hwss0901.model.dto.request.EmployeeCreateDTO;
import ra.hwss0901.model.dto.response.ApiResponse;
import ra.hwss0901.model.entity.Employee;
import ra.hwss0901.service.EmployeeService;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<ApiResponse<Employee>> creatEmployee(@Valid @RequestBody EmployeeCreateDTO employeeCreateDTO) {
        ApiResponse<Employee> response = ApiResponse.<Employee>builder()
                .status("SUCCESS")
                .message("Thêm mới nhân viên thành công!")
                .data(employeeService.createEmployee(employeeCreateDTO))
                .httpStatus(HttpStatus.CREATED)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/{id}/avatar")
    public ResponseEntity<ApiResponse<Employee>> updateAvatar(@PathVariable Long id, @ModelAttribute AvatarUploadDTO avatarUploadDTO) {
        ApiResponse<Employee> response = ApiResponse.<Employee>builder()
                .status("SUCCESS")
                .message("Cập nhật ảnh đại diện thành công!")
                .data(employeeService.uploadAvatar(id, avatarUploadDTO.getAvatarUrl()))
                .httpStatus(HttpStatus.OK)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
