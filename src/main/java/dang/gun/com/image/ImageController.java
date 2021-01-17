package dang.gun.com.image;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {

    private final ImageService imageService;

    @PostMapping(value = "/upload")
    public ResponseEntity upload(@RequestParam("file") List<MultipartFile> file, @RequestParam("id") int postId) throws IOException {

        imageService.saveImage(file, postId);

        return ResponseEntity.ok("SUCCESS");
    }


}