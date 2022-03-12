package hu.alkfejl.controller;

import hu.alkfejl.model.Team;

import java.util.List;

public interface TeamController {

    Team getTeam(int id);

    List<Team> getAllTeams();
}
