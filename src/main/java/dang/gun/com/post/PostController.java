package dang.gun.com.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;


    /**
     * 게시글 등록
     *
     * @param postCreateRequest
     * @return
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity create(@RequestPart("file") List<MultipartFile> fileList, @RequestPart("post") PostCreateRequest postCreateRequest) throws IOException {
        Post post = postService.create(fileList, postCreateRequest);
        return ResponseEntity.ok(new PostDto(post));
    }

    /**
     * 게시글 수정
     *
     * @param postUpdateRequest
     * @return
     */
    @PutMapping
    @ResponseBody
    public ResponseEntity<PostDto> update(@RequestPart("file") List<MultipartFile> fileList, @RequestPart("post")  PostUpdateRequest postUpdateRequest) throws IOException {
        Post post = postService.update(fileList, postUpdateRequest);
        return ResponseEntity.ok(new PostDto(post));
    }

    /**
     * 게시글 삭제
     *
     * @param postDeleteRequest
     * @return
     */
    @DeleteMapping
    @ResponseBody
    public ResponseEntity delete(@RequestBody PostDeleteRequest postDeleteRequest) {
        postService.delete(postDeleteRequest);
        return ResponseEntity.ok().build();
    }

    /**
     * 게시글 상세 조회
     *
     * @param postId
     * @return
     */
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> findOne(@PathVariable(name = "postId") int postId) {
        Post post = postService.findOne(postId);
        return ResponseEntity.ok(new PostDto(post));
    }

    /**
     * 게시글 전체 조회
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List> findAll() {
        List<PostAllDto> postList = postService.findAll();
        return ResponseEntity.ok(postList);
    }
}
