package be.skdebrug.service;

import be.skdebrug.model.Team;
import be.skdebrug.model.comparators.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Developer: Ben Oeyen
 * Date: 10/03/2017
 */

public class TeamService {

    private static TeamService teamService;
    private List<Team> fTeams;

    private TeamService() {
        fTeams = new ArrayList<>();
    }

    public static TeamService getInstance() {
        if (teamService == null) {
            teamService = new TeamService();
        }
        return teamService;
    }

    public List<Team> getAllTeams() {
        return fTeams;
    }

    public List<Team> getAllTeamsOrderByNumberAsc() {
        Collections.sort(fTeams, new TeamSortByNumberComparator());
        return fTeams;
    }

    public List<Team> getAllTeamsOrderByNumberDesc() {
        Collections.sort(fTeams, new TeamSortByNumberComparator());
        Collections.reverse(fTeams);
        return fTeams;
    }

    public List<Team> getAllTeamsOrderByTotalPriceAsc() {
        Collections.sort(fTeams, new TeamSortByTotalPriceComparator());
        return fTeams;
    }

    public List<Team> getAllTeamsOrderByTotalPriceDesc() {
        Collections.sort(fTeams, new TeamSortByTotalPriceComparator());
        Collections.reverse(fTeams);
        return fTeams;
    }

    public void addTeam(Team team) {
        fTeams.add(team);
    }

    public Team getTeam(int id) {
        return fTeams.get(id);
    }

    public void clean() {
        for (Team t : fTeams) {
            t.clean();
        }
        fTeams.clear();
    }
}
