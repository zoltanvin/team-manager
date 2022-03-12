package hu.alkfejl.controller;

import hu.alkfejl.dao.GameDao;
import hu.alkfejl.dao.GameDaoImpl;
import hu.alkfejl.model.Game;

import java.util.List;

public class GameControllerImpl implements GameController {

    private final GameDao dao = new GameDaoImpl();

    public GameControllerImpl() {
    }

    @Override
    public boolean addGame(Game game) {
        return dao.addGame(game);
    }

    @Override
    public List<Game> getAllGamesByTeam(int id) {
        return dao.getAllGamesByTeam(id);
    }

    @Override
    public List<Game> getAllHomeGamesByTeam(int id) {
        return dao.getAllHomeGamesByTeam(id);
    }

    @Override
    public List<Game> getAllAwayGamesByTeam(int id) {
        return dao.getAllAwayGamesByTeam(id);
    }
}
