package dang.gun.com.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @return
     */
    @PostMapping("/singup")
    @ResponseBody
    public ResponseEntity singup(@RequestBody UserSingupDto userSingupDto) {
            userService.singup(userSingupDto);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 중복회원조회
     * @param email
     * @return
     */
    @ResponseBody
    @GetMapping()
     public ResponseEntity<List> findByEmail(@RequestParam(value="email") String email) {
        List<User> user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
     }

    /**
     * 로그인
     * @param userSingupDto
     * @return
     */
     @PostMapping("/singin")
     @ResponseBody
     public ResponseEntity singin(@RequestBody UserSingupDto userSingupDto) {
             //userService.singup(userSingupDto);
         return ResponseEntity.ok("SUCCESS");
     }

}
