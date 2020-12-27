package spring.demos.car_system.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.demos.car_system.domain.binding_models.OfferCreateDto;
import spring.demos.car_system.domain.entities.Offer;
import spring.demos.car_system.domain.enums.Category;
import spring.demos.car_system.domain.enums.Engine;
import spring.demos.car_system.domain.enums.Transmission;

import java.time.LocalDateTime;

@Configuration
public class ApplicationBeanConfiguration {
    private ModelMapper modelMapper;

    @Bean
    public ModelMapper modelMapper() {
        if (this.modelMapper == null) {
            this.modelMapper = new ModelMapper();
        }

        this.initMappings();
        return this.modelMapper;
    }


    private void initMappings() {
        Converter<String, Category> categoryConverter = ctx -> Category.valueOf(ctx.getSource().toUpperCase());
        Converter<String, Engine> engineConverter = ctx -> Engine.valueOf(ctx.getSource().toUpperCase());
        Converter<String, Transmission> transmissionConverter = ctx -> Transmission.valueOf(ctx.getSource().toUpperCase());

        this.modelMapper
                .createTypeMap(OfferCreateDto.class, Offer.class)
                .addMappings(mapping -> mapping
                        .using(categoryConverter)
                        .map(OfferCreateDto::getCategory, Offer::setCategory))
                .addMappings(mapping -> mapping
                        .using(engineConverter)
                        .map(OfferCreateDto::getEngine, Offer::setEngine))
                .addMappings(mapping -> mapping
                        .using(transmissionConverter)
                        .map(OfferCreateDto::getTransmission, Offer::setTransmission));
    }
}