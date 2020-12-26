package spring.demos.car_system.services.interfaces;

import spring.demos.car_system.domain.entities.Offer;
import spring.demos.car_system.domain.entities.enums.Category;

import java.util.List;

public interface OfferService {
    //TODO ADD METHODS AND CREATE AN IMPLEMENTATION
    List<Offer> getAllOffers();

    List<Offer> getOffersByCategory(Category category);

    Offer getOfferById(Long id);

    Offer createOffer(Offer offer);

    Offer updateOffer(Offer offer);

    Offer deleteOffer(Long id);
}
