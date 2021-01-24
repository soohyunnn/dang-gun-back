package dang.gun.com.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     * @param userSignupDto
     * @return
     */
    public void signup(UserSignupDto userSignupDto) {
        User user = new User();

        String EncodedPassword = passwordEncoder.encode(userSignupDto.getPassword());

        user.setEmail(userSignupDto.getEmail());
        user.setName(userSignupDto.getUsername());
        user.setPrevPassword(EncodedPassword);
        user.setPassword(EncodedPassword);
        user.setAddressNumber(userSignupDto.getAddressNumber());
        user.setDetailAddress(userSignupDto.getDetailAddress());
        user.setCreatedAt(LocalDateTime.now());

        log.info(user.toString());

        userRepository.save(user);
    }

    /**
     * 중복회원조회
     * @param email
     * @return
     */
    public Boolean isExistingUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
