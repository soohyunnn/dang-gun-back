package dang.gun.com.post;

import dang.gun.com.image.ImageService;
import dang.gun.com.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
     * @param fileList
     * @param postInputRequest
     * @return
     * @throws IOException
     */
    public Post create(List<MultipartFile> fileList, PostInputRequest postInputRequest) throws IOException {
        Post post = new Post();
        User user = new User();

        user.setId(postInputRequest.getUser().getId());
        user.setName(postInputRequest.getUser().getName());

        post.setTitle(postInputRequest.getTitle());
        post.setContent(postInputRequest.getContent());
        post.setPrice(postInputRequest.getPrice());
        post.setUser(postInputRequest.getUser());

        //게시글 저장
        Post postResponse = postRepository.save(post);

        int postId = postResponse.getId();
        //이미지 저장 시작
        imageService.save(fileList, postId);

        return postResponse;
    }

    /**
     * 게시글 수정
     * @param postInputRequest
     * @return
     */
    public Post update(PostInputRequest postInputRequest) {

        Optional<Post> resultPost = postRepository.findById(postInputRequest.getId());

        if(!resultPost.isPresent()) throw new IllegalArgumentException();    //null일 경우 따로 에러 처리

        resultPost.ifPresent(selectPost -> {
            selectPost.setTitle(postInputRequest.getTitle());
            selectPost.setContent(postInputRequest.getContent());
            selectPost.setPrice(postInputRequest.getPrice());
            selectPost.setModifiedAt(LocalDateTime.now());
            Post post = postRepository.save(selectPost);
        });

        Post postResponse = postRepository.findOneById(resultPost.get().id);

        return postResponse;
    }

    /**
     * 게시글 삭제
     * @param postInputRequest
     */
    public void delete(PostInputRequest postInputRequest){
        Optional<Post> resultPost = postRepository.findById(postInputRequest.getId());

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
    public List<PostListDto> findAll() {
        List<PostListDto> postList = postRepository.findPostBySequenceLimit8(1);
        if(postList == null) throw new IllegalArgumentException();
        return postList;
    }

}
