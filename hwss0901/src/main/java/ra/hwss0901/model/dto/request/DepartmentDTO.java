package ra.hwss0901.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
@Setter
public class DepartmentDTO {
    @NotBlank(message = "Không được để trống tên!")
    @Size(min = 5, max = 50, message = "Độ dài từ 5-50 ký tự!")
    private String name;
    @NotBlank(message = "Không được để trống phòng ban!")
    @Size(max = 100, message = "Tối đa 100 ký tự!")
    private String description;
}
