package spring.demos.car_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.demos.car_system.domain.entities.Brand;
import spring.demos.car_system.domain.entities.Model;
import spring.demos.car_system.domain.entities.enums.Category;
import spring.demos.car_system.repositories.BrandRepository;
import spring.demos.car_system.repositories.ModelRepository;
import spring.demos.car_system.services.interfaces.ModelService;
import spring.demos.car_system.init.ErrorMessage;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public Model getModelByName(String name) {
        return this.modelRepository
                .findByName(name)
                .orElseThrow(ErrorMessage
                        .entityNotFoundException(ErrorMessage.NOT_EXISTING_ENTITY_WITH_NAME, Model.class.getSimpleName(), name));
    }

    @Override
    public Model getModelById(Long id) {
        return this.modelRepository
                .findById(id)
                .orElseThrow(ErrorMessage
                        .entityNotFoundException(ErrorMessage.NOT_EXISTING_ENTITY_WITH_ID, Model.class.getSimpleName(), String.valueOf(id)));
    }

    @Override
    public Model createModel(@Valid Model model) {
        try {
            this.getModelByName(model.getName());
            throw new IllegalArgumentException(String
                    .format(ErrorMessage.ENTITY_WITH_NAME_ALREADY_EXISTS,
                            Model.class.getSimpleName(),
                            model.getName()));

        } catch (EntityNotFoundException enfe) {
            model.setCreated(LocalDateTime.now());
            model.setModified(LocalDateTime.now());

            model.setBrand(this.getRandomBrand());
            model.setCategory(Category.CAR);

            return this.modelRepository.saveAndFlush(model);
        }
    }

    @Override
    public Model updateModel(@Valid Model model) {
        //Throws exception if model with that id doesnt exists!
        Model modelById = this.getModelById(model.getId());

        model.setModified(LocalDateTime.now());
        model.setBrand(modelById.getBrand());
        model.setCreated(modelById.getCreated());
        model.setCategory(Category.MOTORCYCLE);

        return this.modelRepository.saveAndFlush(model);
    }

    @Override
    public Model deleteModel(Long id) {
        //Throws exception if model with that id doesnt exists!
        Model modelById = this.getModelById(id);

        this.modelRepository.deleteById(id);

        return modelById;
    }

    private Brand getRandomBrand(){
        Random random = new Random();
        long index = random.nextInt((int) this.brandRepository.count()) + 1;
        return this.brandRepository
                .findById(index)
                .orElseThrow(ErrorMessage
                        .entityNotFoundException(ErrorMessage.NOT_EXISTING_ENTITY_WITH_ID,
                                Brand.class.getSimpleName(),
                                String.valueOf(index)));
    }
}
