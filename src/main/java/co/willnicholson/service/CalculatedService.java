package co.willnicholson.service;

import co.willnicholson.repository.FightRepository;
import co.willnicholson.repository.FighterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatedService {
    private FighterRepository fighterRepository;
    private FightRepository fightRepository;

    @Autowired
    public CalculatedService(FightRepository f, FighterRepository ff){
        this.fighterRepository = ff;
        this.fightRepository = f;
    }
}
