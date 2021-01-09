package dang.gun.com.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
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

}
