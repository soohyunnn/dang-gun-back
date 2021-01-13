package dang.gun.com.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/users")
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
    @PostMapping("/singup")
    @ResponseBody
    public ResponseEntity<String> singup(@RequestBody UserSingupDto userSingupDto, HttpServletRequest request) throws NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        ResponseEntity<String> entity = null;
        try{
            userService.singup(userSingupDto);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    /**
     * 중복회원조회
     * @param email
     * @return
     */
    @ResponseBody
    @GetMapping("/selectedUser")
     public ResponseEntity<List> findByEmail(@RequestParam(value="email") String email) {
        List<User> user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
     }

}
