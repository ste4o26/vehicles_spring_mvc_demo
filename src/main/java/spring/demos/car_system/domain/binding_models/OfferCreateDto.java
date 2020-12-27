package spring.demos.car_system.domain.binding_models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OfferCreateDto {

    @NonNull
    @NotNull
    private String model;

    @NonNull
    @NotNull
    @Size(min = 5, max = 512)
    private String imageUrl;

    @NonNull
    @NotNull
    @Min(1900)
    private Integer year;

    @NonNull
    @NotNull
    private BigDecimal price;

    @NonNull
    @NotNull
    private Integer mileage;

    @NonNull
    @NotNull
    private String engine;

    @NonNull
    @NotNull
    private String category;

    @NonNull
    @NotNull
    private String transmission;

    @NonNull
    @NotNull
    private String username;

    @NonNull
    @NotNull
    @Size(min = 5, max = 512)
    private String description;
}
