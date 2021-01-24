package dang.gun.com.post;

import dang.gun.com.image.ImageService;
import dang.gun.com.user.User;
import dang.gun.com.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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

        User user = userRepository.findById(postCreateRequest.getUserId()).orElse(null);

        if (Objects.isNull(user)) throw new IllegalArgumentException();

        post.setTitle(postCreateRequest.getTitle());
        post.setContent(postCreateRequest.getContent());
        post.setPrice(postCreateRequest.getPrice());
        post.setUserId(postCreateRequest.getUserId());
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
    public Post update(PostUpdateRequest postUpdateRequest) {
        Post postResponse = postRepository.findById(postUpdateRequest.getPostId()).orElse(null);

        if (Objects.isNull(postResponse)) throw new IllegalArgumentException();

        postResponse.setTitle(postUpdateRequest.getTitle());
        postResponse.setContent(postUpdateRequest.getContent());
        postResponse.setPrice(postUpdateRequest.getPrice());
        postResponse.setModifiedAt(LocalDateTime.now());
        postRepository.save(postResponse);

        return postResponse;
    }

    /**
     * 게시글 삭제
     *
     * @param postDeleteRequest
     */
    public void delete(PostDeleteRequest postDeleteRequest) {
        Post post = postRepository.findById(postDeleteRequest.getPostId()).orElse(null);

        if (Objects.isNull(post)) throw new IllegalArgumentException();

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
