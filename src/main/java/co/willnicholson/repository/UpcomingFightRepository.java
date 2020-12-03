package co.willnicholson.repository;

import co.willnicholson.DTOs.UpcomingFightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpcomingFightRepository extends JpaRepository<UpcomingFightEntity, String> {

    public List<UpcomingFightEntity> findAll();

}
