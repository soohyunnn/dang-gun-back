package dang.gun.com.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post create(PostWHATRequest postRequestDto) {
        Post post = new Post();

        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        post.setPrice(postRequestDto.getPrice());
        post.setUser(postRequestDto.getUser());

        Post postResponse = postRepository.save(post);
        return postResponse;
    }

    public Post update(PostWHATRequest postRequest) {

        Optional<Post> resultPost = postRepository.findById(postRequest.getId());

        if(!resultPost.isPresent()) throw new IllegalArgumentException();    //null일 경우 따로 에러 처리

        resultPost.ifPresent(selectPost -> {
            selectPost.setTitle(postRequest.getTitle());
            selectPost.setContent(postRequest.getContent());
            selectPost.setPrice(postRequest.getPrice());
            selectPost.setModifiedAt(LocalDateTime.now());
            Post post = postRepository.save(selectPost);
        });

        Post postResponse = postRepository.findOneById(resultPost.get().id);

        return postResponse;
    }

    public void delete(PostWHATRequest postRequest){
        Optional<Post> resultPost = postRepository.findById(postRequest.getId());

        if(!resultPost.isPresent()) throw new IllegalArgumentException();    //null일 경우 따로 에러 처리

        resultPost.ifPresent(selectPost -> {
            selectPost.setRemovedAt(LocalDateTime.now());
            Post post = postRepository.save(selectPost);
        });
    }


    public Post findOne(int id) {
        return postRepository.findOneById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

}
