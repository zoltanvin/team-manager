package hu.alkfejl.controller;

import hu.alkfejl.model.Travel;

public interface TravelController {

    int addTravel(Travel travel);

    Travel getTravel(int travelId);
}
