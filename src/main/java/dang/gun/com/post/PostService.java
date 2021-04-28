package dang.gun.com.post;

import dang.gun.com.image.ImageService;
import dang.gun.com.user.User;
import dang.gun.com.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ImageService imageService;
    private final UserRepository userRepository;


    /**
     * 게시글 저장
     *
     * @param fileList
     * @param postCreateRequest
     * @return
     * @throws IOException
     */
    public Post create(List<MultipartFile> fileList, PostCreateRequest postCreateRequest) throws IOException {
        Post post = new Post();

        User user = userRepository.findByEmail(postCreateRequest.getUserEmail()).orElse(null);

        log.info("userEmail", postCreateRequest.getUserEmail());
        log.info("userId", user.getId());

        if (Objects.isNull(user)) throw new IllegalArgumentException();

        post.setTitle(postCreateRequest.getTitle());
        post.setContent(postCreateRequest.getContent());
        post.setPrice(postCreateRequest.getPrice());
        post.setUserId(user.getId());
        post.setUser(user);

        //게시글 저장
        Post postResponse = postRepository.save(post);

        int postId = postResponse.getId();
        //이미지 저장
        imageService.save(fileList, postId);

        return postResponse;
    }

    /**
     * 게시글 수정
     *
     * @param postUpdateRequest
     * @return
     */
    public Post update(List<MultipartFile> fileList, PostUpdateRequest postUpdateRequest) throws IOException {
        Post postResponse = postRepository.findById(postUpdateRequest.getPostId()).orElse(null);
        if (Objects.isNull(postResponse)) throw new IllegalArgumentException();

        postResponse.setTitle(postUpdateRequest.getTitle());
        postResponse.setContent(postUpdateRequest.getContent());
        postResponse.setPrice(postUpdateRequest.getPrice());
        postResponse.setModifiedAt(LocalDateTime.now());
        postRepository.save(postResponse);

        int postId = postResponse.getId();
        //이미지 수정
        imageService.update(fileList, postId);

        return postResponse;
    }

    /**
     * 게시글 삭제
     * @param postId
     * @param userEamil
     */
    public void delete(int postId,  String userEamil) {
        Post post = postRepository.findById(postId).orElse(null);
        if (Objects.isNull(post)) throw new IllegalArgumentException();

        Optional<User> user = userRepository.findByEmail(userEamil);
        if(post.getUserId() != user.get().getId()){
            throw new IllegalArgumentException();   //전달받은 user의 ID와 삭제하려는 Post를 작성한 user의 ID가 다르면 에러 발생
        }

        post.setRemovedAt(LocalDateTime.now());
        postRepository.save(post);

    }

    /**
     * 상세 게시글 조회
     *
     * @param postId
     * @return
     */
    public Post findOne(int postId) {
        return postRepository.findById(postId).orElse(null);
    }

    /**
     * 게시글 전체 조회
     *
     * @return
     */
    public List<PostAllDto> findAll() {
        List<PostAllDto> postList = postRepository.findPostBySequenceLimit8(1);
        if (postList.isEmpty()) throw new IllegalArgumentException();
        return postList;
    }

}
