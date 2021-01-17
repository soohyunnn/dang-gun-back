package dang.gun.com.image;

import dang.gun.com.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/images ")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {

    private final ImageService imageService;
    private final S3Uploader s3Uploader;


    @PostMapping(value = "/upload")
    public List<String> upload(@RequestParam("file") List<MultipartFile> file, @RequestParam("id") int postId) throws IOException {
        String imgPath;
        List<String> resultPath = new ArrayList<String>();

        for (int i = 0; i < file.size(); i++) {
            System.out.println("file" + i + " : " + file.get(i));
            imgPath = s3Uploader.upload(file.get(i));
            resultPath.add(imgPath);

            System.out.println("i: " + i + "file = " + file);
            System.out.println(file.get(i).getName());
            System.out.println(file.get(i).getContentType());

            Post post = new Post();
            post.setId(postId);

            Image image = new Image();
            image.setName(file.get(i).getOriginalFilename());
            image.setPath(imgPath);
            image.setType(file.get(i).getContentType());
            image.setPost(post);
            imageService.saveImage(image);

        }


        return resultPath;
    }


}
