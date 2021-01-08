package dang.gun.com.post;

import dang.gun.com.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public int save(Post post){
        System.out.println("post"+post.getContent());
        postRepository.save(post);
        return post.getId();
    }

    public Optional<Post> findOne(int id){
        return postRepository.findById(id);
    }

    public List<Post> findPostList(){
        return postRepository.findAll();
    }

}
