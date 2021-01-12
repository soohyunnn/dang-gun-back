package dang.gun.com.user;

import dang.gun.com.AES256Cipher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
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

    public int singup(UserSingupDto userSingupDto) throws NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        User user = new User();

        AES256Cipher aes256Cipher = new AES256Cipher();
        String password_encode = aes256Cipher.AES_Encode(userSingupDto.password);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String date = now.format(formatter);

        user.setEmail(userSingupDto.email);
        user.setUsername(userSingupDto.username);
        user.setPrev_password(password_encode);
        user.setPassword(password_encode);
        user.setAddressnumber(userSingupDto.addressnumber);
        user.setDetailaddress(userSingupDto.detailaddress);
        user.setCreated_at(now);
        user.setModified_at(now);
        user.setRemoved_at(now);


        //String password_decode = aes256Cipher.AES_Decode(password_encode);

        //System.out.println("password_encode = " + password_encode);
        //System.out.println("password_decode = " + password_decode);

        log.info(user.toString());

        userRepository.save(user);
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
