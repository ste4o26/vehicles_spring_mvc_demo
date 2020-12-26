package spring.demos.car_system.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import spring.demos.car_system.domain.entities.enums.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "users")
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class User extends BaseEntity {

    @NotNull
    @Size(max = 60)
    @Column(name = "username", length = 60, nullable = false, unique = true)
    private String username;

    //TODO add pattern validation for the password
    @NotNull
    @Size(max = 60)
    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @NotNull
    @Size(max = 60)
    @Column(name = "first_name", length = 60)
    private String firstName;

    @NotNull
    @Size(max = 60)
    @Column(name = "last_name", length = 60)
    private String lastName;

    @NotNull
    @Column(name = "active")
    private boolean active;

    @ElementCollection
    private Set<Role> roles;

    @Column(name = "image_url")
    private String imageUrl;

    @NotNull
    @PastOrPresent
    @Column(name = "created")
    private LocalDateTime created;

    @NotNull
    @PastOrPresent
    @Column(name = "modified")
    private LocalDateTime modified;

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
