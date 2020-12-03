package co.willnicholson.analysis;

import co.willnicholson.DTOs.FightDTO;
import co.willnicholson.DTOs.FighterDTO;
import co.willnicholson.service.FightService;
import co.willnicholson.service.FighterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MatchupAnalysis {
    private FighterService fighterService;
    private FightService fightService;

    @Autowired
    public MatchupAnalysis(FighterService f, FightService s){
        this.fighterService = f;
        this.fightService = s;
    }

    public String getHistoricalWeight(String fighter1, String fighter2){
        //Get list of wins and losses for both fighters
        //For each fighter1Win, if the fight1Win loser matches a fighter2Loss winner, add to fighter1HistoricalWeight

        List<FightDTO> fighter1Wins = fightService.getWinsByFighter(fighter1);
        List<FightDTO> fighter1Losses = fightService.getLossesByFighter(fighter1);
        List<FightDTO> fighter2Wins = fightService.getWinsByFighter(fighter2);
        List<FightDTO> fighter2Losses = fightService.getLossesByFighter(fighter2);
        int fighter1HistoricalWeight = 0;
        int fighter2HistoricalWeight = 0;
        for(FightDTO f1Wins: fighter1Wins){
            for(FightDTO f2Losses: fighter2Losses){
                if(f1Wins.getFighter2().equalsIgnoreCase(f2Losses.getFighter1())){
                    fighter1HistoricalWeight++;
                }
            }
        }
        log.info(fighter1Wins.toString());
        log.info(String.valueOf(fighter1Wins.size()));
        log.info(fighter1 + " Historical Weight: " + fighter1HistoricalWeight);
        for(FightDTO f2Wins: fighter2Wins){
            for(FightDTO f1Losses: fighter1Losses){
                if(f2Wins.getFighter2().equalsIgnoreCase(f1Losses.getFighter1())){
                    fighter2HistoricalWeight++;
                }
            }
        }
        log.info(fighter2 + " Historical Weight: " + fighter2HistoricalWeight);
        return fighter1 + " Historical Weight: " + fighter1HistoricalWeight + ", " + fighter2 + " Historical Weight: " + fighter2HistoricalWeight;
    }
    public double getWinRatio(String name){
        List<FighterDTO> fighterList = fighterService.getFighterByName(name);
        double winRatio = 0;
        if(fighterList.size() == 1){
            FighterDTO fighter = fighterList.get(0);
            double winCoef = fighter.getWinsKo() + fighter.getWinsSub() + (.7 * (fighter.getWinsOther() + fighter.getWinsDec()));
            double lossCoef = fighter.getLossesKo() + fighter.getLossesSub() + (.7 * (fighter.getLossesOther() + fighter.getLossesDec()));
            winRatio = winCoef / lossCoef;
        }
        return winRatio;
    }

}
