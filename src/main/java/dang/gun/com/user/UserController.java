package dang.gun.com.user;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 회원가입
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/user/save")
    public ResponseEntity<String> save(User user, HttpServletRequest request){

        userService.save(user);

        //클라이언트 정보 - ip 주소 반환
        return ResponseEntity.ok(request.getRemoteAddr());
    }

    /**
     * 중복회원조회
     * @param email
     * @return
     */
    @ResponseBody
    @GetMapping("/user/selectedUser")
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
