package com.example.service.impl;

import com.example.dao.GamesStepsDao;
import com.example.entity.GamesSteps;
import com.example.service.BuildNumbersService;
import com.example.service.GenerateRandomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static java.util.stream.Collectors.*;

@Service
public class BuildNumbersServiceImpl implements BuildNumbersService {
    @Autowired
    GamesStepsDao gamesStepsDao;
    @Autowired
    GenerateRandomService generateRandomService;
    @Override
    public String getBuildNumbersForComputer(GamesSteps gamesSteps,Map<String,Integer> useNum) {
        gamesStepsDao.addSteps(gamesSteps);
        String theMainNum = null;
//        if(gamesSteps.getQty().equalsIgnoreCase("0") && gamesSteps.getMatCol().equalsIgnoreCase("0")){
//            return generateRandomService.getGenerateNumberThenZero(gamesSteps.getNumberGamer());
//        }
        List<GamesSteps> listSteps = gamesStepsDao.getListNumbers(gamesSteps.getRefGame());

        if(!listSteps.isEmpty()) {
            theMainNum = listSteps.get(0).getNumberGamer();
            System.out.println("listSteps -------------- " + listSteps.toString());
            System.out.println("listSteps -------------- " + gamesSteps.toString());
            gamesSteps.setQty(listSteps.get(0).getQty());
            gamesSteps.setMatCol(listSteps.get(0).getMatCol());
            //getCheckSuccesNumber(listSteps,useNum);
            String the75Proccent = String.valueOf(listSteps.get(0).getNumberGamer().length() * 75 / 100);

            if(listSteps.get(0).getQty().equalsIgnoreCase(the75Proccent) || listSteps.get(0).getMatCol().equalsIgnoreCase(the75Proccent)){

                String tmp = null;
                StringBuilder maybeNumber = new StringBuilder();
                for (int i = 0; i < theMainNum.length(); i++) {
                if (!gamesSteps.getNumberGamer().contains(String.valueOf(theMainNum.charAt(i)))) {
                    tmp = String.valueOf(theMainNum.charAt(i));
                    maybeNumber.append(tmp);
                }
            }
                System.out.println("tmp " + tmp);

                String ttt = null;
                if (tmp != null) {
                    for (int i = 0; i < gamesSteps.getNumberGamer().length(); i++) {
                        if (!theMainNum.contains(String.valueOf(gamesSteps.getNumberGamer().charAt(i)))) {
                            ttt = String.valueOf(gamesSteps.getNumberGamer().charAt(i));
                        }
                    }
                }
                    System.out.println("ttt " + ttt);
                Map<String,String> number = new HashMap<>();
                if(ttt != null) {
                    List<GamesSteps> gg = gamesStepsDao.get75MatchesNotNumUse(gamesSteps.getRefGame(), "1", tmp);
                    System.out.println(gamesStepsDao.get75MatchesNotNumUse(gamesSteps.getRefGame(), "1", tmp));
                    for (GamesSteps f : gg) {
                        for (int i = 0; i < theMainNum.length(); i++) {
                            if (f.getNumberGamer().contains(String.valueOf(theMainNum.charAt(i))) && !tmp.equalsIgnoreCase(String.valueOf(theMainNum.charAt(i)))) {
                                number.put(String.valueOf(theMainNum.charAt(i)),String.valueOf(theMainNum.charAt(i)));
                            }
                        }
                    }

                    List<GamesSteps> hh = gamesStepsDao.get75MatchesNumUse(gamesSteps.getRefGame(),"0");
                    if(!hh.isEmpty()){
                        for (GamesSteps df : hh) {
                            for (Map.Entry<String, String> entry : number.entrySet()) {
                                if(df.getNumberGamer().contains(entry.getKey())
                                        && df.getMatCol().equalsIgnoreCase("2")
                                        && df.getQty().equalsIgnoreCase("2") && !maybeNumber.toString().contains(entry.getKey())){
                                    maybeNumber.append(entry.getKey());
                                }
                                if(df.getNumberGamer().contains(entry.getKey())
                                        && df.getMatCol().equalsIgnoreCase("1")
                                        && df.getQty().equalsIgnoreCase("1")
                                        && df.getNumberGamer().contains(tmp)){
                                    number.remove(entry.getKey());
                                }
                            }
                        }
                    }
                    System.out.println(hh.toString());
                    System.out.println(useNum.size());
                    int i = 0;
                    while(i<10){
                        if(!useNum.containsKey(String.valueOf(i))){
                            number.put(String.valueOf(i),String.valueOf(i));
                        }
                        i++;
                    }
                    System.out.println(number);
                    System.out.println(maybeNumber.toString());
                    while (maybeNumber.toString().length() < 4) {
                        int boundedRandomValue = ThreadLocalRandom.current().nextInt(0, 10);
                            if (number.toString().contains(String.valueOf(boundedRandomValue)) && !maybeNumber.toString().contains(String.valueOf(boundedRandomValue))) {
                                maybeNumber.append(number.get(String.valueOf(boundedRandomValue)));
                            }
                    }
                    List<GamesSteps> gfhgfh = gamesStepsDao.findByRefGameAndNumberGamerContains(gamesSteps.getRefGame(),tmp);
                    System.out.println(maybeNumber.toString());
                    System.out.println(gfhgfh.toString());
                    return maybeNumber.toString();
                }
        }
        }
        final int numLength = listSteps.get(0).getNumberGamer().length();
        final int [] notUseNum = generateRandomService.getGenerateNumbersNotUse(0,numLength,Integer.valueOf(gamesSteps.getQty()));
        StringBuilder stringBuilder = new StringBuilder();
        for(int y : notUseNum){
            stringBuilder.append(listSteps.get(0).getNumberGamer().charAt(y));
        }
        return generateRandomService.getGenerateNumbers(numLength - Integer.valueOf(gamesSteps.getQty()), stringBuilder);
    }

    @Override
    public Map<String, Integer> getUseNumber(String number, Map<String, Integer> useNum) {
        for (int i = 0; i < number.length(); i++){
            int tmp = 1;
            if(useNum.containsKey(String.valueOf(number.charAt(i)))){
                tmp = useNum.get(String.valueOf(number.charAt(i))) + 1;
            }
            useNum.put(String.valueOf(number.charAt(i)), tmp);
        }
        return useNum;
    }

    @Override
    public String getBuildNumberThen75(List<GamesSteps> listSteps, Map<String, Integer> useNum) {
        final String the75Proccent = String.valueOf(listSteps.get(0).getNumberGamer().length() * 75 / 100);
//        if(listSteps.get(0).getQty().equalsIgnoreCase(the75Proccent) || listSteps.get(0).getMatCol().equalsIgnoreCase(the75Proccent)){
//            getBuildNumberThen75(listSteps,useNum);
//        }
        final String theMainNum = listSteps.get(0).getNumberGamer();
        Map<String, Integer> iiiii = new HashMap<>();
        List<Map<Integer, String>> h = new ArrayList<>();
        for(int i =0; i < theMainNum.length(); i++){
            final String tmp = String.valueOf(theMainNum.charAt(i));
            for(GamesSteps gs : listSteps){
                Map<Integer, String> tt = new HashMap<>();
                System.out.println("----------- gs--------------- " + gs.toString());
                if(!gs.getMatCol().equalsIgnoreCase("0") && gs.getNumberGamer().contains(tmp)){
                    System.out.println("----------- gs.getNumberGamer() --------------- " + gs.getNumberGamer());
                    tt.put(gs.getNumberGamer().indexOf(tmp),tmp);
                    if(gs.getMatCol().equalsIgnoreCase("1") && gs.getQty().equalsIgnoreCase("1")){
                        iiiii.put(tmp,gs.getNumberGamer().indexOf(tmp));
                    }
                    System.out.println("----------- tt --------------- " + tt);
                    h.add(tt);
                }
            }
        }
        String template = "X0|X1|X2|X3";
        if(!iiiii.isEmpty()){
            for (Map.Entry<String, Integer> entry : iiiii.entrySet()) {
                template = template.replace("X" + entry.getValue(), entry.getKey());
        }
        }
        String repeat = null;
        for (Map<Integer, String> vv : h) {
            for (Map.Entry<Integer, String> entry : vv.entrySet()) {
                if(!iiiii.containsValue(entry.getKey())){
                    template = template.replace("X" + entry.getKey(), entry.getValue());
                }
            }
        }
        System.out.println(h.toString());
        System.out.println(iiiii);
        System.out.println(template);
        return null;
    }

    private String getCheckSuccesNumber(List<GamesSteps> listSteps,Map<String,Integer> useNum){
        Map<String, Integer> budget = new HashMap<>();
        List<String> ex = new ArrayList<>();
        for(GamesSteps gs : listSteps){
            final int count = Integer.valueOf(gs.getMatCol());
            if(count > 0 && count*100/gs.getNumberGamer().length() >= 50){
                System.out.println("------------- " + gs.getNumberGamer());
                ex.add(gs.getNumberGamer());
                for (int i = 0; i < gs.getNumberGamer().length(); i++){
                    int tmp = 1;
                    if(budget.containsKey(String.valueOf(gs.getNumberGamer().charAt(i)))){
                        tmp = budget.get(String.valueOf(gs.getNumberGamer().charAt(i))) + 1;
                    }
                    budget.put(String.valueOf(gs.getNumberGamer().charAt(i)), tmp);
                }
            }
        }
        Map<String, Integer> sorted = budget
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,LinkedHashMap::new));
        Map<String, Integer> gg = new HashMap<>();
        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(value * 100 / listSteps.get(0).getNumberGamer().length() > 50){
                int y = 1;
                Map<Integer, Integer> ffff = new HashMap<>();
                for(String t :ex){
                    int f = t.indexOf(key);
                    System.out.println("-------- " + f + " " + key + " " + t.indexOf(key)

                    );
                    if(f >= 0 && ffff.containsKey(f)){
                        y++;
                    }
                    if(f >= 0 || ffff.isEmpty()){
                        ffff.put(f,y);
                    }
                    System.out.println("-------- " + ffff);
                    if(y * 100 / value  >= 50 && f >= 0){
                        gg.put(key,f);
                        y = 1;
                    }
                }
            }
            }

        System.out.println(sorted);
        System.out.println(gg);
        String template = "X0|X1|X2|X3";
//        for (Map.Entry<String, Integer> entry : sorted.entrySet()) {
//            String key = entry.getKey();
//            Integer value = entry.getValue();
//            if(value * 100 / listSteps.get(0).getNumberGamer().length()  >= 50){
//                if(!gg.containsKey(key)){
//                    int b = 0;
//                    while(b<listSteps.get(0).getNumberGamer().length()){
//                        if(!gg.containsValue(b)){
//                            gg.put(key,b);
//                            break;
//                        }
//                        b++;
//                    }
//                }
//            }
//        }
//        for (Map.Entry<String, Integer> entry : gg.entrySet()) {
//            template = template.replace("X"+entry.getValue(),entry.getKey());
//        }
//        template = template.replace("|","");
        System.out.println(gg);
        System.out.println(template);
        return template;
    }
}
