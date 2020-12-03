package co.willnicholson.repository;

import co.willnicholson.DTOs.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    public UserEntity findFirstByPhoneContaining(String phone);

    @Query(value = "update users set name = :name, profile = :profile where phone = :phone", nativeQuery = true)
    public void updateUser(@Param("name") String name, @Param("profile") String profile, @Param("phone") String phone);

}
