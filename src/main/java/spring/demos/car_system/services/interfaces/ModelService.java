package spring.demos.car_system.services.interfaces;

import spring.demos.car_system.domain.entities.Model;

import java.util.List;

public interface ModelService {
    //TODO ADD METHODS AND CREATE AN IMPLEMENTATION
//    List<Model> getAllModelsByBrandName();

    Model getModelByName(String name);

    Model getModelById(Long id);

    Model createModel(Model model);

    Model updateModel(Model model);

    Model deleteModel(Long id);
}
