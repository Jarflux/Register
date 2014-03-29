/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.comparators;

import Model.Team;
import java.util.Comparator;

/**
 *
 * @author Ben
 */
public class TeamSortByNumberComparator implements Comparator<Team>{

    @Override
    public int compare(Team t1, Team t2) {
        return t1.getNumber() > t2.getNumber() ? 1 : -1;
    }    
}
