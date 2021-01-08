package dang.gun.com.user;

import org.springframework.stereotype.Service;

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

}
