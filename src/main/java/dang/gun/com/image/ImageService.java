package dang.gun.com.image;

import dang.gun.com.post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final S3Uploader s3Uploader;


    public void saveImage(List<MultipartFile> file, int postId) throws IOException {

        for (int i = 0; i < file.size(); i++) {
            Image image = new Image();
            String imgPath = s3Uploader.upload(file.get(i));

            Post post = new Post();
            post.setId(postId);

            image.setName(file.get(i).getOriginalFilename());
            image.setPath(imgPath);
            image.setType(file.get(i).getContentType());
            image.setPost(post);
            imageRepository.save(image);
        }
    }
}
