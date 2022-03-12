package hu.alkfejl.dao;

import hu.alkfejl.model.Game;

import java.util.List;

public interface GameDao {

    boolean addGame(Game game);

    List<Game> getAllGamesByTeam(int teamId);

    List<Game> getAllHomeGamesByTeam(int teamId);

    List<Game> getAllAwayGamesByTeam(int teamId);
}
