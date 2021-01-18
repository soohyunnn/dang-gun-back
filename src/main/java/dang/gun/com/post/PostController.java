package dang.gun.com.post;

import dang.gun.com.image.ImageService;
import dang.gun.com.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/boards")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {

    private final PostService postService;


    /**
     * 게시글 등록
     *
     * @param //postRequest
     * @return
     */
//    @PostMapping
//    @ResponseBody
//    public ResponseEntity<PostDto> create(@RequestBody PostWHATRequest postRequest) {
//        Post post = postService.create(postRequest);
//        return ResponseEntity.ok(new PostDto(post));
//    }

    @PostMapping
    @ResponseBody
    public ResponseEntity create1(@RequestPart("file") List<MultipartFile> file, HttpServletRequest requset) throws IOException {
        Post post = postService.create(file, requset);
        return ResponseEntity.ok(new PostDto(post));
    }


    /**
     * 게시글 수정
     *
     * @param postRequest
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<PostDto> update(@RequestBody PostWHATRequest postRequest) {
        Post post = postService.update(postRequest);
        return ResponseEntity.ok(new PostDto(post));
    }

    /**
     * 게시글 삭제
     *
     * @param postRequest
     * @return
     */
    @PostMapping("/delete")
    @ResponseBody
    public ResponseEntity delete(@RequestBody PostWHATRequest postRequest) {
        postService.delete(postRequest);
        return ResponseEntity.ok("SUCCESS");
    }

    /**
     * 게시글 상세 조회
     *
     * @param id
     * @return
     */
    @GetMapping("/post")
    public ResponseEntity<PostDto> findOne(@RequestParam int id) {
        Post post = postService.findOne(id);
        return ResponseEntity.ok(new PostDto(post));
    }

    /**
     * 게시글 전체 조회s
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List> findAll() {
        List<Post> postList = postService.findAll();
        return ResponseEntity.ok(postList);
    }
}
