package core;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Constants.constants;
import Utils.utils;
import models.teams;

public class coreProcess {

    private TreeMap<String, Integer> scoreResults;
    private List<teams> orderFinalResults;

    public coreProcess(TreeMap<String, Integer> scoreResults, List<teams> orderFinalResults) {
        this.scoreResults = scoreResults;
        this.orderFinalResults = orderFinalResults;
    }

    public boolean checkExpression(String text){

        boolean result = false;
        Pattern pattern = Pattern.compile(constants.validScoreLine);
        Matcher matcher = pattern.matcher(text);
        result = matcher.find();

        return result;

    }

    public void calculateScore(String record){

        String[] splitRecord = record.split(",");
        Map<String, Integer> team1 = utils.processNameAndScoreTeam(splitRecord[0]);
        Map<String, Integer> team2 = utils.processNameAndScoreTeam(splitRecord[1]);

        team1.forEach((key,value)->{
            
            team2.forEach((key2,value2)->{
                if(value==value2){
                    processScore(key, value, constants.scoreTie);
                    processScore(key2, value2, constants.scoreTie);
                }else if(value>value2){
                    processScore(key, value, constants.scoreWin);
                    processScore(key2, value2, constants.scoreLose);
                }else if(value2>value){
                    processScore(key2, value2, constants.scoreWin);
                    processScore(key, value, constants.scoreLose);
                }
            });
        });
    
    }

    private void processScore(String key, Integer value, int score){

        String keyWithoutSpaces = utils.trimStartAndEndSpace(key);
        if(scoreResults.containsKey(keyWithoutSpaces.toUpperCase())){
            scoreResults.put(keyWithoutSpaces.toUpperCase(), scoreResults.get(keyWithoutSpaces.toUpperCase())+score);
        }else{
            scoreResults.put(keyWithoutSpaces.toUpperCase(), score);
        }
        
    }

    public void showFinalResults(){

        scoreResults.forEach((key,value)->{
            teams team = new teams(key,value);
            orderFinalResults.add(team);
        });
    
        Collections.sort(orderFinalResults, Collections.reverseOrder());
    
        if(orderFinalResults.size()>0) {

            for(teams t: orderFinalResults){
                System.out.println(t.getTeamName()+" - "+t.getScoreTeam()+" "+constants.points+"\n");
            }
    
            orderFinalResults.clear();
            scoreResults.clear();


        }else{
            System.out.println("There were not results to process");
        }

    }
    
}
