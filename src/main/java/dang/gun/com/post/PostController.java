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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    private final PostService postService;


    /**
     * 게시글 등록
     *
     * @param postInputRequest
     * @return
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity create(@RequestPart("file") List<MultipartFile> fileList, @RequestPart("post") PostInputRequest postInputRequest) throws IOException {
        Post post = postService.create(fileList, postInputRequest);
        return ResponseEntity.ok(new PostDto(post));
    }

    /**
     * 게시글 수정
     *
     * @param postInputRequest
     * @return
     */
    @PutMapping
    @ResponseBody
    public ResponseEntity<PostDto> update(@RequestBody PostInputRequest postInputRequest) {
        Post post = postService.update(postInputRequest);
        return ResponseEntity.ok(new PostDto(post));
    }

    /**
     * 게시글 삭제
     *
     * @param postInputRequest
     * @return
     */
    @DeleteMapping
    @ResponseBody
    public ResponseEntity delete(@RequestBody PostInputRequest postInputRequest) {
        postService.delete(postInputRequest);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 게시글 상세 조회
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> findOne(@PathVariable(name = "id") int id) {
        Post post = postService.findOne(id);
        return ResponseEntity.ok(new PostDto(post));
    }

    /**
     * 게시글 전체 조회
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List> findAll() {
        List<PostListDto> postList = postService.findAll();
        return ResponseEntity.ok(postList);
    }
}
