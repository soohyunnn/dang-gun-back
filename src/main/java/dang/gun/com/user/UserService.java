package dang.gun.com.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public int singup(UserSingupDto userSingupDto){
        User user = new User();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String date = now.format(formatter);

        user.setEmail(userSingupDto.email);
        user.setUsername(userSingupDto.username);
        user.setPassword(userSingupDto.password);
        user.setAddressnummber(userSingupDto.addressnumber);
        user.setDetailaddress(userSingupDto.detailaddress);
        user.setCreated_at(now);
        user.setModified_at(now);
        user.setRemoved_at(now);

        log.info(user.toString());

        //userRepository.save(user);
        return user.getId();
    }

    public List<User> getByEmail(String email){
        System.out.println("email = " + email);
        System.out.println("aaa::"+userRepository.findByEmail(email));
        return userRepository.findByEmail(email);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

}
