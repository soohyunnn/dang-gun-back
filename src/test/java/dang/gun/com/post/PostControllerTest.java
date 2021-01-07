package dang.gun.com.post;

import dang.gun.com.user.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostControllerTest {

    @Autowired PostService postService;

    @Test
    public void save() throws ParseException {
        User user = new User();
        Post post = new Post();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");

        String date = now.format(formatter);

        user.setId(1);
        user.setEmail("soohyunnn@gmail.com");
        user.setUsername("soo");
        user.setPassword("qweasd12");
        user.setPrev_password("qweasd12");
        user.setAddress("경기도 하남시");
        user.setCreated_at(now);
        user.setModified_at(now);
        user.setRemoved_at(now);

        post.setId(1);
        post.setTitle("Test한다잉");
        post.setContent("잘 들어가야할텐데 말이지");
        post.setView_cnt(0);
        post.setLike_cnt(0);
        post.setCreated_at(now);
        post.setModified_at(now);
        post.setRemoved_at(now);
        //post.setUser_id(user.getId());

        System.out.println("user::"+user.getId());
        //System.out.println("post::"+post.getUser_id());

        int id = postService.save(post);

        Post post1 = postService.findOne(id).get();
        assertThat(post.getTitle()).isEqualTo(post1.getTitle());


    }

}