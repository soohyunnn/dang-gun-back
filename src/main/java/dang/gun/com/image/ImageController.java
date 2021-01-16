package dang.gun.com.image;

import dang.gun.com.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;
    private final S3Uploader s3Uploader;


    @PostMapping(value = "/upload", produces = "application/json")
    public String upload(@RequestParam("data") MultipartFile file, @RequestParam("id") int id) throws IOException {
        String imgPath = s3Uploader.upload(file);

        System.out.println("file = " + file);
        System.out.println(file.getName());
        System.out.println(file.getContentType());

        Post post = new Post();
        post.setId(id);

        Image image = new Image();
        image.setImgName(file.getOriginalFilename());
        image.setImgPath(imgPath);
        image.setImgType(file.getContentType());
        image.setPost(post);
        imageService.saveImage(image);

        return imgPath;
    }
}
