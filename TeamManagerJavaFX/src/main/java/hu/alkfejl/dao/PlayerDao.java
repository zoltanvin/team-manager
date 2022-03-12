package hu.alkfejl.dao;

import hu.alkfejl.model.Message;
import hu.alkfejl.model.Player;

import java.util.List;

public interface PlayerDao {

    Player getPlayer(int playerId);

    List<Player> getAllPlayersByTeam(int teamId);

    boolean sendMessage(Message message);

    Message getMessage(int messageId);

    List<Player> getMessageSenderPlayers(int teamId);

    List<Player> getMessageReceiverPlayers(int teamId);

    List<Player> getAllActivePlayersByTeamAndGame(int teamId, int gameId);

}
