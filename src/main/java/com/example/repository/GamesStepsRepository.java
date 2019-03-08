/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.repository;

import com.example.entity.GamesSteps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author OLYA
 */
@Repository
public interface GamesStepsRepository extends JpaRepository<GamesSteps, Integer> {

    List<GamesSteps> findTop10ByRefGameOrderByQtyDescMatColDesc(String ref);
    //List<GamesSteps> findByRefGameAndCountMatchesAndCountNumber(String ref, String countMatches, String countNumber);
    List<GamesSteps> findByRefGameAndMatColIsOrRefGameAndQtyIs(String ref, String countMatches, String st,String countNumber);
    List<GamesSteps> findByRefGameAndQtyNotAndNumberGamerContains(String ref,String countNumber, String num);
    List<GamesSteps> findByRefGameAndMatColNot(String ref,String num);
    List<GamesSteps> findByRefGameAndNumberGamerContains(String ref,String num);
}
