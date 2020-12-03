package co.willnicholson.repository;

import co.willnicholson.DTOs.FighterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FighterRepository extends JpaRepository<FighterEntity, String> {
    public List<FighterEntity> findAllByNameContaining(String name);
    public List<FighterEntity> findAllOrderById(int id);

}
