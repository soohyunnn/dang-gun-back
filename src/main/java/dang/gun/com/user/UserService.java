package dang.gun.com.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public int singup(UserSingupDto userSingupDto){
        User user = new User();
        LocalDateTime now = LocalDateTime.now();

        String passwordEncoding = passwordEncoder.encode(userSingupDto.password);

        user.setEmail(userSingupDto.email);
        user.setUsername(userSingupDto.username);
        user.setPrevPassword(passwordEncoding);
        user.setPassword(passwordEncoding);
        user.setAddressNumber(userSingupDto.addressnumber);
        user.setDetailAddress(userSingupDto.detailaddress);
        user.setCreatedAt(now);
        user.setModifiedAt(now);
        user.setRemovedAt(now);

        log.info(user.toString());

        userRepository.save(user);
        return user.getId();
    }

    public List<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User singin(UserSingupDto userSingupDto) {
        User user = (User) userRepository.findByEmail(userSingupDto.email);
        if(user != null){
            passwordEncoder.matches(userSingupDto.password, user.getPassword());
            return user;
        }
        return null;
    }

}
