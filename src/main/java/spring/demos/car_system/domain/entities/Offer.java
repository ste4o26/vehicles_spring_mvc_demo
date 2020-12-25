package spring.demos.car_system.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import spring.demos.car_system.domain.entities.enums.Category;
import spring.demos.car_system.domain.entities.enums.Engine;
import spring.demos.car_system.domain.entities.enums.Transmission;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "offers")
@Table(name = "offers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Offer extends BaseEntity {

    @NotNull
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Size(max = 512)
    @Column(name = "description", columnDefinition = "TEXT", length = 512)
    private String description;

    @NotNull
    @Column(name = "engine")
    @Enumerated(EnumType.STRING)
    private Engine engine;

    @Size(max = 512)
    @Column(name = "image_url", length = 512)
    private String imageUrl;

    @NotNull
    @Column(name = "mile_age")
    private Integer mileAge;

    @NotNull
    @Column(name = "price")
    private BigDecimal price;

    @NotNull
    @Column(name = "transmission")
    @Enumerated(EnumType.STRING)
    private Transmission transmission;

    @NotNull
    @Column(name = "year")
    @PastOrPresent//may not work correctly with integer!!!
    @Min(1900)
    private Integer year;

    @NotNull
    @PastOrPresent
    @Column(name = "created")
    private LocalDateTime created;

    @NotNull
    @PastOrPresent
    @Column(name = "modified")
    private LocalDateTime modified;

    @NotNull
    @ManyToOne(targetEntity = Model.class)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private Model model;

    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private User seller;
}
