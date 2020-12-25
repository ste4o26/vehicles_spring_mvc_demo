package spring.demos.car_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.demos.car_system.domain.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
