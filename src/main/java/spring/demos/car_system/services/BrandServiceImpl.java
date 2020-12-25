package spring.demos.car_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.demos.car_system.domain.entities.Brand;
import spring.demos.car_system.repositories.BrandRepository;
import spring.demos.car_system.services.interfaces.BrandService;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Brand getBrandByName(String name) {
        return this.brandRepository
                .findByName(name)
                .orElseThrow(() -> new IllegalArgumentException(String.
                        format("No brand with name %s has been found!",
                                name)));
    }

    @Override
    public Brand getBrandById(Long id) {
        return null;
    }
}
