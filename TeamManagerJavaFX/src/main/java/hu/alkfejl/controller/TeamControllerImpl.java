package hu.alkfejl.controller;

import hu.alkfejl.dao.TeamDao;
import hu.alkfejl.dao.TeamDaoImpl;
import hu.alkfejl.model.Team;

import java.util.List;

public class TeamControllerImpl implements TeamController {

    private final TeamDao dao = new TeamDaoImpl();

    public TeamControllerImpl() {
    }

    @Override
    public Team getTeam(int id) {
        return dao.getTeam(id);
    }

    @Override
    public List<Team> getAllTeams() {
        return dao.getAllTeams();
    }
}
