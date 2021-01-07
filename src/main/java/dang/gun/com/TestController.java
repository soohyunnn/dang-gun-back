package dang.gun.com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

//    @Autowired
//    TestService testService;

    @GetMapping("/1")
    @ResponseBody
    public String test () {
        System.out.println("Test");
        return "Test";
    }

//    @GetMapping("/")
//    public String test1(){
//        testService.createTest();
//        return"test1";
//    }
//
//    @GetMapping("/test")
//    @ResponseBody
//    public String test2(){
//        Test test = testService.getTest();
//        return test.name_u;
//    }

}
