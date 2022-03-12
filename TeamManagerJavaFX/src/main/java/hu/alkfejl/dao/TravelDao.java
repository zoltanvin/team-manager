package hu.alkfejl.dao;

import hu.alkfejl.model.Travel;

public interface TravelDao {

    int addTravel(Travel travel);

    Travel getTravel(int travelId);
}
