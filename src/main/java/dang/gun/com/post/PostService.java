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


    /**
     * 게시글 저장
     * @param file
     * @param postRequest
     * @return
     * @throws IOException
     */
    public Post create(List<MultipartFile> file, PostCreateRequest postRequest) throws IOException {
        Post post = new Post();
        User user = new User();

        user.setId(postRequest.getUser().getId());
        user.setName(postRequest.getUser().getName());

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

    /**
     * 게시글 수정
     * @param postRequest
     * @return
     */
    public Post update(PostCreateRequest postRequest) {

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

    /**
     * 게시글 삭제
     * @param postRequest
     */
    public void delete(PostCreateRequest postRequest){
        Optional<Post> resultPost = postRepository.findById(postRequest.getId());

        if(!resultPost.isPresent()) throw new IllegalArgumentException();    //null일 경우 따로 에러 처리

        resultPost.ifPresent(selectPost -> {
            selectPost.setRemovedAt(LocalDateTime.now());
            Post post = postRepository.save(selectPost);
        });
    }

    /**
     * 상세 게시글 조회
     * @param id
     * @return
     */
    public Post findOne(int id) {
        return postRepository.findOneById(id);
    }

    /**
     * 게시글 전체 조회
     * @return
     */
    public List<PostAllDto> findAll() {
        List<PostAllDto> postList = postRepository.findPostBySequenceLimit8Inner(1);
        if(postList == null) throw new IllegalArgumentException();
        return postList;
    }

}
