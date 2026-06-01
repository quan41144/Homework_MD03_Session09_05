package ra.hwss0901.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;
    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(length = 15, nullable = false, unique = true)
    private String phone;
    @Column(nullable = false)
    private Double salary;
    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
