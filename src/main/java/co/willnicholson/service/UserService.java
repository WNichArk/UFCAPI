package co.willnicholson.service;

import co.willnicholson.DTOs.UserEntity;
import co.willnicholson.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository u){
        this.userRepository = u;
    }

    public void saveUser(String phone){
        UserEntity userEntity = UserEntity.builder().name("NewSave").active(1).phone(phone).profile("2,5").build();
        userRepository.save(userEntity);
    }


}
