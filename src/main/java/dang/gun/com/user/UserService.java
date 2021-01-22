package dang.gun.com.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public int signup(UserSignupDto userSignupDto) {
        User user = new User();
        LocalDateTime now = LocalDateTime.now();

        String passwordEncoding = passwordEncoder.encode(userSignupDto.password);

        user.setEmail(userSignupDto.email);
        user.setName(userSignupDto.username);
        user.setPrevPassword(passwordEncoding);
        user.setPassword(passwordEncoding);
        user.setAddressNumber(userSignupDto.addressNumber);
        user.setDetailAddress(userSignupDto.detailAddress);
        user.setCreatedAt(now);
        user.setModifiedAt(now);
        user.setRemovedAt(now);

        log.info(user.toString());

        userRepository.save(user);
        return user.getId();
    }

    public Boolean isExistingUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User signin(UserSignupDto userSingupDto) {
        User user = (User) userRepository.findByEmail(userSingupDto.email);
        if (user == null) throw new IllegalArgumentException();
        passwordEncoder.matches(userSingupDto.password, user.getPassword());
        return user;

    }

}
