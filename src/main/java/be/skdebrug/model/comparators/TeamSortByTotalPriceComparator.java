package be.skdebrug.model.comparators;

import be.skdebrug.model.Team;
import java.util.Comparator;

/**
 *
 * @author Ben
 */
public class TeamSortByTotalPriceComparator implements Comparator<Team>{

    @Override
    public int compare(Team t1, Team t2) {
        return t1.getTotalPrice().compareTo(t2.getTotalPrice());
    }
    
}
