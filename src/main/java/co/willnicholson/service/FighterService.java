package co.willnicholson.service;

import co.willnicholson.repository.FighterRepository;
import co.willnicholson.DTOs.FighterDTO;
import co.willnicholson.DTOs.FighterEntity;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FighterService {
    private FighterRepository repository;
    private MapperFacade mapper;

    @Autowired
    public FighterService(FighterRepository r, MapperFacade m){
    this.repository = r;
    this.mapper = m;
    }

    public List<FighterDTO> getFighterByName(String name){
        List<FighterEntity> entities = repository.findAllByNameContaining(name);
        List<FighterDTO> retVal = new ArrayList<>();
        for(FighterEntity e: entities){
            FighterDTO dto = mapper.map(e, FighterDTO.class);
            retVal.add(dto);
        }
        return retVal;
    }
}
