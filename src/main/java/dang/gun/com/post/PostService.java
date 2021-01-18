package dang.gun.com.post;

import dang.gun.com.image.ImageService;
import dang.gun.com.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ImageService imageService;


    public Post create(List<MultipartFile> file, HttpServletRequest requset) throws IOException {
        Post post = new Post();
        User user = new User();


        String title = requset.getParameter("title");
        String content = requset.getParameter("content");
        String price = requset.getParameter("price");
        String userID = requset.getParameter("userID");
        String userName = requset.getParameter("userName");

        user.setId(Integer.parseInt(userID));
        user.setName(userName);

        PostWHATRequest postRequest = new PostWHATRequest(0,title, content, Integer.parseInt(price), user);

        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setPrice(postRequest.getPrice());
        post.setUser(postRequest.getUser());

        //게시글 저장
        Post postResponse = postRepository.save(post);

        int postId = postResponse.getId();
        //이미지 저장 시작
        imageService.save(file, postId);

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
