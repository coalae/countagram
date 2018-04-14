package at.ac.univie.countagram.logic;

import android.app.Activity;

import java.util.List;

import at.ac.univie.countagram.model.Competition;
import at.ac.univie.countagram.repository.CompetitionRepository;

/**
 * CompetitionManager class manages the interaction with the repository, i.e. it extracts data from the
 * repository and also updates data in the repository.
 */

public class CompetitionManager {

    /**
     * Instance variable competitionRepository
     */
    private CompetitionRepository competitionRepository;

    /**
     * Constructor
     */
    public CompetitionManager(){
        competitionRepository = new CompetitionRepository();
    }

    /**
     * Adds a given Competition to the repository
     * @param competition
     */
    public void addCompetition(Competition competition){
        competitionRepository.addCompetition(competition);
    }

    /**
     * Finds a Competition (using the userId as a parameter) in the repository
     * @param user_id
     * @return
     */
    public List<Competition> findCompetitionByUserId(int user_id){
        return competitionRepository.getCaloryIntakeByUserId(user_id);
    }

}
