package dang.gun.com.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "수현이";
    }

    @GetMapping("/filtered/login")
    public String login1() {
        return "영섭이";
    }
}
