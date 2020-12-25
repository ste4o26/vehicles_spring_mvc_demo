package spring.demos.car_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.demos.car_system.domain.entities.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}
