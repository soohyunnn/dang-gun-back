package dang.gun.com.post;

import org.springframework.stereotype.Controller;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    public void save(Post post){
        postService.save(post);
    }
}
