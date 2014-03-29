/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Team;
import Model.comparators.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: Ben Oeyen
 * @date: 9-feb-2013
 */
public class TeamController {

    private static TeamController teamController;
    private List<Team> fTeams;

    private TeamController() {
        fTeams = new ArrayList<>();
    }

    public static TeamController getInstance() {
        if (teamController == null) {
            teamController = new TeamController();
        }
        return teamController;
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
