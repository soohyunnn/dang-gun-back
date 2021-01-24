package dang.gun.com.image;

import kotlin.collections.ArrayDeque;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {

    private final ImageService imageService;

    /**
     * 게시글 상세 이미지 슬라이드 목록 조회
     * @param postId
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<List> findAllByPostId(@PathVariable(name = "id") int postId) {
        List<Image> imageList = imageService.findAllByPostId(postId);
        List<ImageDto> responseImageList = new ArrayDeque<>();

        for (Image image : imageList) {
            ImageDto imageDto = new ImageDto(image);
            responseImageList.add(imageDto);
        }

        return ResponseEntity.ok(responseImageList);
    }

}

