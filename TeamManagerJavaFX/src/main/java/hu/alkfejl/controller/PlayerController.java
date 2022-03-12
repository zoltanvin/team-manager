package hu.alkfejl.controller;

import hu.alkfejl.model.Message;
import hu.alkfejl.model.Player;

import java.util.List;

public interface PlayerController {

    Player getPlayer(int playerId);

    List<Player> getAllPlayersByTeam(int teamId);

    boolean sendMessage(Message message);

    List<Player> getMessageSenderPlayers(int teamId);

    List<Player> getMessageReceiverPlayers(int teamId);

    List<Player> getAllActivePlayersByTeamAndGame(int teamId, int gameId);

}
