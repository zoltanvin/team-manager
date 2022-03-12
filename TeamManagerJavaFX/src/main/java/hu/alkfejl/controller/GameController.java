package hu.alkfejl.controller;

import hu.alkfejl.model.Game;

import java.util.List;

public interface GameController {

    boolean addGame(Game game);

    List<Game> getAllGamesByTeam(int id);

    List<Game> getAllHomeGamesByTeam(int id);

    List<Game> getAllAwayGamesByTeam(int id);
}
