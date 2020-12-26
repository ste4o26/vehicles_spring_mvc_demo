package spring.demos.car_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.demos.car_system.domain.entities.Offer;
import spring.demos.car_system.domain.entities.enums.Category;
import spring.demos.car_system.repositories.OfferRepository;
import spring.demos.car_system.services.interfaces.OfferService;
import spring.demos.car_system.init.ErrorMessage;

import javax.validation.Valid;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public List<Offer> getAllOffers() {
        return this.offerRepository.findAll();
    }

    @Override
    public List<Offer> getOffersByCategory(Category category) {
        return this.offerRepository
                .findAllByCategory(category)
                .orElseThrow(ErrorMessage.entityNotFoundException(ErrorMessage.NOT_EXISTING_OFFER_WITH_CATEGORY,
                        Offer.class.getSimpleName(),
                        category.toString()));
    }

    @Override
    public Offer getOfferById(Long id) {
        return this.offerRepository
                .findById(id)
                .orElseThrow(ErrorMessage.entityNotFoundException(ErrorMessage.NOT_EXISTING_ENTITY_WITH_ID,
                        Offer.class.getSimpleName(),
                        String.valueOf(id)));
    }

    @Override
    public Offer createOffer(@Valid Offer offer) {
      //TODO implement when the gui(web page) is ready
        return null;
    }

    @Override
    public Offer updateOffer(Offer offer) {
        //TODO implement when the gui(web page) is ready
        return null;
    }

    @Override
    public Offer deleteOffer(Long id) {
        Offer offerById = this.getOfferById(id);

        this.offerRepository.deleteById(id);

        return offerById;
    }
}
