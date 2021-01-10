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
     public JSONObject getByEmail(@RequestParam(value="email") String email) {
        System.out.println("UserController.getByEmail");
        System.out.println("email = " + email);
        JSONObject json = new JSONObject();
        try{
            List<User> user = userService.getByEmail(email);
            System.out.println("user = " + user);
            json.put("result", "SUCCESS");
            json.put("data", user);
            return json;
        } catch (Exception e){
            e.printStackTrace();
            json.put("result", "FAIL");
            return json;
        }
     }

     @ResponseBody
     @GetMapping("/users")
     public JSONObject hello(){
        JSONObject json = new JSONObject();

        try{
            List<User> users = userService.findAll();
            System.out.println("user = " + users);
            json.put("result", "SUCCESS");
            json.put("data", users);
            return json;
        } catch (Exception e){
            e.printStackTrace();
            json.put("result", "FAIL");
            return json;
        }


     }

}
