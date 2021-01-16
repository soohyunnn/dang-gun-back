package dang.gun.com.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     *
     * @param userDto
     * @return
     */
    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity signup(@RequestBody UserDto userDto) {
        userService.signup(userDto);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 중복회원조회
     *
     * @param email
     * @return
     */
    @ResponseBody
    @GetMapping
    public ResponseEntity<Boolean> isExistingUserByEmail(@RequestParam(value = "email") String email) {
        return ResponseEntity.ok(userService.isExistingUserByEmail(email));
    }

    /**
     * 로그인
     *
     * @param userDto
     * @return
     */
    @PostMapping("/signin")
    @ResponseBody
    public ResponseEntity signin(@RequestBody UserDto userDto) {
        userService.signin(userDto);
        return ResponseEntity.ok("SUCCESS");
    }

}
