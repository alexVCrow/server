package com.example.service;


import com.example.entity.GamesSteps;

import java.util.List;
import java.util.Map;

public interface BuildNumbersService {
    String getBuildNumbersForComputer(GamesSteps gamesSteps,Map<String,Integer> useNum);
    Map<String,Integer> getUseNumber(String number,Map<String, Integer> useNum);
    String getBuildNumberThen75(List<GamesSteps> listSteps, Map<String,Integer> useNum);

}
