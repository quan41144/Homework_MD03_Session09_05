package ra.hwss0901.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeCreateDTO {
    @NotBlank(message = "Họ và tên không được để trống")
    private String fullName;
    @NotBlank(message = "Không được để trống email!")
    @Email(message = "Không đúng định dạng email!")
    private String email;
    @NotBlank(message = "Không được để trống số điện thoại!")
    @Pattern(regexp = "^(03|05|07|08|09)\\d{8}$", message = "Không đúng định dạng số điện thoại!")
    private String phone;
    @NotNull(message = "Không được để trống lương!")
    @Min(value = 5000000, message = "Lương tối thiểu là 5000000")
    private Double salary;
    @NotNull(message = "Mã phòng không được để trống!")
    private Long departmentId;
}
