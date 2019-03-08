package com.example.controller;

import com.example.dao.GamesStepsDao;
import com.example.dto.GameRequest;
import com.example.dto.GameStartResponseDto;
import com.example.service.BuildNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/games")
public class GamesController {

    @Autowired
    BuildNumbersService buildNumbersService;
    @Autowired
    GamesStepsDao gamesStepsDao;

    @PostMapping("/steps1")
    public ResponseEntity addSteps(@RequestBody GameRequest gameRequest) {
        final String num = buildNumbersService.getBuildNumbersForComputer(gameRequest.getGamesSteps(),gameRequest.getUseNumber());
        GameStartResponseDto start = new GameStartResponseDto();
        start.setNumber(num);
        start.setUuid(gameRequest.getGamesSteps().getRefGame());
        start.setUseNum(buildNumbersService.getUseNumber(num,gameRequest.getUseNumber()));
        return ResponseEntity.ok(start);
    }

//    @PostMapping("/steps1")
//    public ResponseEntity addSteps1(@RequestBody GameRequest gameRequest) {
//        final String num = buildNumbersService.getBuildNumbersForComputer(gameRequest.getGamesSteps(),gameRequest.getUseNumber());
//        GameStartResponseDto start = new GameStartResponseDto();
//        start.setNumber(num);
//        start.setUuid(gameRequest.getGamesSteps().getRefGame());
//        start.setUseNum(buildNumbersService.getUseNumber(num,gameRequest.getUseNumber()));
//        int f = gameRequest.getGamesSteps().getNumberGamer().length() * 75 / 100;
//        int g = (int)Math.floor((double) f);
//        System.out.println(g);
//        System.out.println(gamesStepsDao.get75MatchesOrCounts(gameRequest.getGamesSteps().getRefGame(), String.valueOf(g), String.valueOf(g)));
//        return ResponseEntity.ok(start);
//    }
}
