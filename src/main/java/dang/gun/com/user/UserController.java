package dang.gun.com.user;

import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void save(User user){
        userService.save(user);
    }

}
