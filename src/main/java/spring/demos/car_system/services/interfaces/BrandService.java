package spring.demos.car_system.services.interfaces;

import spring.demos.car_system.domain.entities.Brand;

import java.time.LocalDateTime;
import java.util.List;

public interface BrandService {
    //TODO ADD METHODS AND CREATE AN IMPLEMENTATION
    List<Brand> getAllBrands();

    Brand getBrandByName(String name);

    Brand getBrandById(Long id);

    Brand createBrand(Brand brand);

    Brand updateBrand(Brand brand);

    Brand deleteBrand(Long id);

    Long getBrandsCount();
}
