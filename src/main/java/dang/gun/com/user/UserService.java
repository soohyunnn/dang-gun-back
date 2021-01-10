package dang.gun.com.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int save(User user){
        userRepository.save(user);
        return user.getId();
    }

    public List<User> getByEmail(String email){
        System.out.println("email = " + email);
        System.out.println("aaa::"+userRepository.findAllByEmail(email));
        return userRepository.findAllByEmail(email);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

}
