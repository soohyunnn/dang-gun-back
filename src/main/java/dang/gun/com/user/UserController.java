package dang.gun.com.user;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 회원가입
     * @param userSingupDto
     * @param request
     * @return
     */
    @PostMapping("/users/singup")
    @ResponseBody
    public ResponseEntity<UserSingupDto> singup(@RequestBody UserSingupDto userSingupDto, HttpServletRequest request) throws NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        log.info("singup Start");
        userService.singup(userSingupDto);
        return ResponseEntity.ok(userSingupDto);
    }

    /**
     * 중복회원조회
     * @param email
     * @return
     */
    @ResponseBody
    @GetMapping("/users/selectedUser")
     public ResponseEntity getByEmail(@RequestParam(value="email") String email) {
        System.out.println("UserController.getByEmail");
        System.out.println("email = " + email);
        List<User> user = userService.getByEmail(email);
        return ResponseEntity.ok(user);
     }

     @ResponseBody
     @GetMapping("/users")
     public ResponseEntity hello() {
         List<User> users = userService.findAll();
         return ResponseEntity.ok(users);
     }

}
