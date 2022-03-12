package hu.alkfejl.dao;

import hu.alkfejl.model.Team;

import java.util.List;

public interface TeamDao {

    Team getTeam(int teamId);

    List<Team> getAllTeams();

}
