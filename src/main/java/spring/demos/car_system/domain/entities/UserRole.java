package spring.demos.car_system.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import spring.demos.car_system.domain.entities.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "user_roles")
@Table(name = "user_roles")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class UserRole extends BaseEntity {

    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @NotNull
    @Column(name = "role")
    private Role role;
}
