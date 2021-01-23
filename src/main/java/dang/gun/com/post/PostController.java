package dang.gun.com.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
     * @param //postRequest
     * @return
     */
    @PostMapping
    @ResponseBody
    public ResponseEntity create1(@RequestPart("file") List<MultipartFile> file, @RequestPart("post") PostCreateRequest postRequest) throws IOException {
        Post post = postService.create(file, postRequest);
        return ResponseEntity.ok(new PostDto(post));
    }

    /**
     * 게시글 수정
     *
     * @param postRequest
     * @return
     */
    @PutMapping
    @ResponseBody
    public ResponseEntity<PostDto> update(@RequestBody PostCreateRequest postRequest) {
        Post post = postService.update(postRequest);
        return ResponseEntity.ok(new PostDto(post));
    }

    /**
     * 게시글 삭제
     *
     * @param postRequest
     * @return
     */
    @DeleteMapping
    @ResponseBody
    public ResponseEntity delete(@RequestBody PostCreateRequest postRequest) {
        postService.delete(postRequest);
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
        List<PostAllDto> postList = postService.findAll();
        return ResponseEntity.ok(postList);
    }
}
