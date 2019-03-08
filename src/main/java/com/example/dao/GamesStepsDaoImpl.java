package com.example.dao;

import com.example.entity.GamesSteps;
import com.example.repository.GamesStepsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GamesStepsDaoImpl implements GamesStepsDao {
    @Autowired
    GamesStepsRepository gamesStepsRepository;
    @Override
    @Transactional
    public void addSteps(GamesSteps gamesSteps) {
        gamesStepsRepository.save(gamesSteps);
    }

    @Override
    public List<GamesSteps> getListNumbers(final String ref) {
        return gamesStepsRepository.findTop10ByRefGameOrderByQtyDescMatColDesc(ref);
    }

    @Override
    public List<GamesSteps> getZeroMatchesAndCounts(String ref, String countMatches, String countNumber) {
        return gamesStepsRepository.findByRefGameAndMatColIsOrRefGameAndQtyIs(ref,countMatches,ref,countNumber);
    }

    @Override
    public List<GamesSteps> get75MatchesOrCounts(String ref, String countMatches, String countNumber) {
        return gamesStepsRepository.findByRefGameAndMatColIsOrRefGameAndQtyIs(ref, countMatches, ref,countNumber);
    }

    @Override
    public List<GamesSteps> get75MatchesNotNumUse(String ref, String countNumber, String num) {
        return gamesStepsRepository.findByRefGameAndQtyNotAndNumberGamerContains(ref, countNumber, num);
    }

    @Override
    public List<GamesSteps> get75MatchesNumUse(String ref, String num) {
        return gamesStepsRepository.findByRefGameAndMatColNot(ref,num);
    }

    @Override
    public List<GamesSteps> findByRefGameAndNumberGamerContains(String ref, String num) {
        return gamesStepsRepository.findByRefGameAndNumberGamerContains(ref,num);
    }
}
