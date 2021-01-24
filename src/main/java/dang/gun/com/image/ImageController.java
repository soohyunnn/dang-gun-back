package dang.gun.com.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {

    private final ImageService imageService;

    /**
     * 게시글 상세 이미지 슬라이드 목록 조회
     *
     * @param postId
     * @return
     */
    @GetMapping("/{postId}")
    public ResponseEntity<List<ImageDto>> findAllByPostId(@PathVariable(name = "postId") int postId) {
        List<Image> imageList = imageService.findAllByPostId(postId);
        List<ImageDto> responseImageList = imageList.stream().map(ImageDto::new).collect(Collectors.toList());

        return ResponseEntity.ok(responseImageList);
    }

}

