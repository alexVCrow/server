package com.example.dao;

import com.example.entity.Customer;
import com.example.entity.GamesSteps;

import java.util.List;

public interface GamesStepsDao {

    void addSteps(GamesSteps gamesSteps);

    List<GamesSteps> getListNumbers(String ref);

    List<GamesSteps> getZeroMatchesAndCounts(String ref, String countMatches, String countNumber);

    List<GamesSteps> get75MatchesOrCounts(String ref, String countMatches, String countNumber);

    List<GamesSteps> get75MatchesNotNumUse(String ref, String countNumber, String num);
    List<GamesSteps> get75MatchesNumUse(String ref, String num);
    List<GamesSteps> findByRefGameAndNumberGamerContains(String ref, String num);

}
