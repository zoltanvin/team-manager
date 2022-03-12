package hu.alkfejl.controller;

import hu.alkfejl.model.Message;
import hu.alkfejl.dao.PlayerDao;
import hu.alkfejl.dao.PlayerDaoImpl;
import hu.alkfejl.model.Player;

import java.util.List;

public class PlayerControllerImpl implements PlayerController {

    private final PlayerDao dao = new PlayerDaoImpl();

    public PlayerControllerImpl() {
    }

    @Override
    public Player getPlayer(int playerId) {
        return dao.getPlayer(playerId);
    }

    @Override
    public List<Player> getAllPlayersByTeam(int teamId) {
        return dao.getAllPlayersByTeam(teamId);
    }

    @Override
    public boolean sendMessage(Message message) {
        return dao.sendMessage(message);
    }

    @Override
    public List<Player> getMessageSenderPlayers(int teamId) {
        return dao.getMessageSenderPlayers(teamId);
    }

    @Override
    public List<Player> getMessageReceiverPlayers(int teamId) {
        return dao.getMessageReceiverPlayers(teamId);
    }

    @Override
    public List<Player> getAllActivePlayersByTeamAndGame(int teamId, int gameId) {
        return dao.getAllActivePlayersByTeamAndGame(teamId, gameId);
    }

}
