package spring.demos.car_system.services.interfaces;

import spring.demos.car_system.domain.entities.Brand;

public interface BrandService {
    //TODO ADD METHODS AND CREATE AN IMPLEMENTATION
    Brand getBrandByName(String name);

    Brand getBrandById(Long id);
}
