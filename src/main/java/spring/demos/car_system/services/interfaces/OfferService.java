package spring.demos.car_system.services.interfaces;

import spring.demos.car_system.domain.binding_models.OfferCreateDto;
import spring.demos.car_system.domain.entities.Offer;
import spring.demos.car_system.domain.enums.Category;

import java.util.List;

public interface OfferService {
    //TODO ADD METHODS AND CREATE AN IMPLEMENTATION
    List<Offer> getAllOffers();

    List<Offer> getOffersByCategory(Category category);

    Offer getOfferById(Long id);

    Offer createOffer(OfferCreateDto offer);

    Offer updateOffer(OfferCreateDto offer);

    Offer deleteOffer(Long id);
}
