package spring.demos.car_system.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import spring.demos.car_system.domain.binding_models.OfferCreateDto;
import spring.demos.car_system.domain.entities.Model;
import spring.demos.car_system.domain.entities.Offer;
import spring.demos.car_system.services.interfaces.BrandService;
import spring.demos.car_system.services.interfaces.ModelService;
import spring.demos.car_system.services.interfaces.OfferService;

import java.util.List;

@Controller
@RequestMapping(value = "/offers")
public class OfferController {
    private final OfferService offerService;
    private final BrandService brandService;
    private final ModelService modelService;

    @Autowired
    public OfferController(OfferService offerService, BrandService brandService, ModelService modelService) {
        this.offerService = offerService;
        this.brandService = brandService;
        this.modelService = modelService;
    }

    @GetMapping(value = "/all")
    public ModelAndView allOffersView(ModelAndView modelAndView) {
        modelAndView.setViewName("all");

        List<Offer> allOffers = this.offerService.getAllOffers();
        modelAndView.addObject("offers", allOffers);

        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView getOfferForm(ModelAndView modelAndView, @ModelAttribute(name = "offer") OfferCreateDto offer){
        modelAndView.setViewName("offer-add");

        List<Model> allModels = this.modelService.getAllModels();

        modelAndView.addObject("models", allModels);

        return modelAndView;
    }

    @PostMapping(value = "/add")
    public RedirectView postOfferForm(@ModelAttribute(name = "offer") OfferCreateDto offer){
        this.offerService.createOffer(offer);
        return new RedirectView("/offers/all");
    }
}
