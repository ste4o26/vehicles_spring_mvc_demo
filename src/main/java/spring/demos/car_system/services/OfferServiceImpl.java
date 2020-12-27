package spring.demos.car_system.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.demos.car_system.domain.binding_models.OfferCreateDto;
import spring.demos.car_system.domain.entities.Model;
import spring.demos.car_system.domain.entities.Offer;
import spring.demos.car_system.domain.entities.User;
import spring.demos.car_system.domain.enums.Category;
import spring.demos.car_system.domain.enums.Role;
import spring.demos.car_system.repositories.ModelRepository;
import spring.demos.car_system.repositories.OfferRepository;
import spring.demos.car_system.repositories.UserRepository;
import spring.demos.car_system.services.interfaces.OfferService;
import spring.demos.car_system.init.ErrorMessage;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, UserRepository userRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
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
    public Offer createOffer(@Valid OfferCreateDto offerCreateDto) {
        Offer offer = this.modelMapper.map(offerCreateDto, Offer.class);
        offer.setCreated(LocalDateTime.now());
        offer.setModified(LocalDateTime.now());

        String username = offerCreateDto.getUsername();
        User seller = this.userRepository
                .findByUsername(username)
                .orElseThrow(ErrorMessage.entityNotFoundException(ErrorMessage.NOT_EXISTING_ENTITY_WITH_NAME,
                        User.class.getSimpleName(),
                        username));

        seller.getRoles().add(Role.SELLER);
        offer.setSeller(seller);

        String modelName = offerCreateDto.getModel();
        Model model = this.modelRepository
                .findByName(modelName)
                .orElseThrow(ErrorMessage.entityNotFoundException(ErrorMessage.NOT_EXISTING_ENTITY_WITH_NAME,
                        Model.class.getSimpleName(),
                        modelName));

        offer.setModel(model);

        return this.offerRepository.saveAndFlush(offer);
    }

    @Override
    public Offer updateOffer(@Valid OfferCreateDto offerCreateDto) {
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
