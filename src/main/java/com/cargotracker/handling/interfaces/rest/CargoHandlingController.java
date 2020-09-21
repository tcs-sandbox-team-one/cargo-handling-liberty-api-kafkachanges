package com.cargotracker.handling.interfaces.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cargotracker.handling.application.internal.commandservices.HandlingActivityRegistrationCommandService;
import com.cargotracker.handling.interfaces.rest.dto.HandlingActivityRegistrationResource;
import com.cargotracker.handling.interfaces.rest.transform.HandlingActivityRegistrationCommandDTOAssembler;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller    // This means that this class is a Controller
@RequestMapping("/cargohandling")
public class CargoHandlingController {
    private HandlingActivityRegistrationCommandService handlingActivityRegistrationCommandService; // Application Service Dependency
    
    
    //private final CargoHandlingKafkaProducer cargoHandlingKafkaProducer ;
    

    /**
     * Provide the dependencies
     * @param cargoBookingCommandService
     */
    @Autowired
    public CargoHandlingController(HandlingActivityRegistrationCommandService handlingActivityRegistrationCommandService){
		this.handlingActivityRegistrationCommandService = handlingActivityRegistrationCommandService;
    }

    /**
     * POST method to register Handling Activities
     * @param handlingActivityRegistrationResource
     */
    @PostMapping
    @ResponseBody
    public String registerHandlingActivity(@RequestBody HandlingActivityRegistrationResource handlingActivityRegistrationResource){
    	
        System.out.println("***CargoHandlingController***"+handlingActivityRegistrationResource.getBookingId());
        System.out.println("***CargoHandlingController***"+handlingActivityRegistrationResource.getHandlingType());

        handlingActivityRegistrationCommandService.registerHandlingActivityService
        (HandlingActivityRegistrationCommandDTOAssembler.toCommandFromDTO(handlingActivityRegistrationResource));
        
		/*
		 * CargoHandledEventData cargoHandledEventData = new
		 * CargoHandledEventData(handlingActivityRegistrationResource.getBookingId(),
		 * java.sql.Date.valueOf(handlingActivityRegistrationResource.getCompletionTime(
		 * )), handlingActivityRegistrationResource.getUnLocode(),
		 * handlingActivityRegistrationResource.getHandlingType(),
		 * handlingActivityRegistrationResource.getVoyageNumber());
		 * 
		 * CargoHandledEvent cargoHandledEvent = new
		 * CargoHandledEvent(cargoHandledEventData); //
		 * this.cargoHandlingKafkaProducer.sendMessage(cargoHandledEventData);
		 * cargoEventPublisherService.handleCargoHandledEvent(cargoHandledEvent);
		 */
        return "Handling Activity Registered";
    }
}
