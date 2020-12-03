package co.willnicholson.repository;

import co.willnicholson.DTOs.FightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FightRepository extends JpaRepository<FightEntity, String> {

    List<FightEntity> findAllByFighter1Containing(String fighter1);
    List<FightEntity> findAllByFighter2Containing(String fighter2);
    List<FightEntity> findAllByWinRound(int winRound);
    List<FightEntity> findAllByWinMethodContainingOrderByDate(String winMethod);
    List<FightEntity> findAllByOrderByDateAsc();
}
