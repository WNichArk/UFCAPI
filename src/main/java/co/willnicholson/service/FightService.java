package co.willnicholson.service;

import co.willnicholson.repository.FightRepository;
import co.willnicholson.DTOs.FightDTO;
import co.willnicholson.DTOs.FightEntity;
import co.willnicholson.DTOs.UpcomingFightDTO;
import co.willnicholson.DTOs.UpcomingFightEntity;
import co.willnicholson.repository.UpcomingFightRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FightService {
    private FightRepository repository;
    private UpcomingFightRepository upcomingFightRepository;
    private MapperFacade mapper;

    @Autowired
    public FightService(FightRepository r, MapperFacade m, UpcomingFightRepository u) {
        this.repository = r;
        this.mapper = m;
        this.upcomingFightRepository = u;
    }

    public List<FightDTO> getFightsByWinRound(int winRound){

        List<FightEntity> entities = repository.findAllByWinRound(winRound);
        return mapper.mapAsList(entities, FightDTO.class);
    }

    public List<FightDTO> getWinsByFighter(String name){

        List<FightEntity> entities = repository.findAllByFighter1Containing(name);
        return mapper.mapAsList(entities, FightDTO.class);
    }

    public List<FightDTO> getLossesByFighter(String name){

        List<FightEntity> entities = repository.findAllByFighter2Containing(name);
        return mapper.mapAsList(entities, FightDTO.class);
    }

    public List<FightDTO> getFightsByWinMethod(String winmethod){

        List<FightEntity> entities = repository.findAllByWinMethodContainingOrderByDate(winmethod);
        return mapper.mapAsList(entities, FightDTO.class);
    }

    public List<FightDTO> getAllFights(){
        List<FightEntity> entities = repository.findAllByOrderByDateAsc();
        return mapper.mapAsList(entities, FightDTO.class);
    }

    public List<UpcomingFightDTO> getUpcomingFights(){
    List<UpcomingFightEntity> entities = upcomingFightRepository.findAll();
    return mapper.mapAsList(entities, UpcomingFightDTO.class);
    }

    public String getUpcomingFightsPretty(){
        List<UpcomingFightEntity> entities = upcomingFightRepository.findAll();
        String htmlOpen = "<html> <head> <style> .neuDiv {color: #d0c0b7; margin-left: 100px; margin-right: 100px; margin-top: 50px; margin-bottom:50px; padding: 25px; border-radius: 35px;\n" +
                "background: #545e64;\n" +
                "box-shadow:  20px 20px 60px #475055, \n" +
                "             -20px -20px 60px #616c73;" +
                "             -20px -20px 60px #5a7584;} body{ background-color: #545e64 }</style> </head>  <body>";
        String add = "";
        String htmlClose = "</html>";
        for(UpcomingFightEntity e: entities){
            add += "<div class=\"neuDiv\"><h1><br><br> " + e.getFighter1name() + "</h1></div>";
        }
        return htmlOpen + add + htmlClose;
    }

}
