package spring.demos.car_system.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity(name = "brands")
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Brand extends BaseEntity{

    @NotNull
    @Size(max = 60)
    @Column(name = "name", length = 60)
    private String name;

    @NotNull
    @PastOrPresent
    @Column(name = "created")
    private LocalDateTime created;

    @NotNull
    @PastOrPresent
    @Column(name = "modified")
    private LocalDateTime modified;
}
