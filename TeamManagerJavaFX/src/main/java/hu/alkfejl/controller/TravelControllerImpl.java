package hu.alkfejl.controller;

import hu.alkfejl.dao.TravelDao;
import hu.alkfejl.dao.TravelDaoImpl;
import hu.alkfejl.model.Travel;

public class TravelControllerImpl implements TravelController {

    private final TravelDao dao = new TravelDaoImpl();

    public TravelControllerImpl() {
    }

    @Override
    public int addTravel(Travel travel) {
        return dao.addTravel(travel);
    }

    @Override
    public Travel getTravel(int travelId) {
        return dao.getTravel(travelId);
    }
}
