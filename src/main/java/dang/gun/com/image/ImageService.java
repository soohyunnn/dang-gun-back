package dang.gun.com.image;

import dang.gun.com.post.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final S3Uploader s3Uploader;


    public void save(List<MultipartFile> file, int postId) throws IOException {
        int cnt = 1;
        for (int i = 0; i < file.size(); i++) {
            Image image = new Image();
            String imgPath = s3Uploader.upload(file.get(i));

            Post post = new Post();
            post.setId(postId);

            image.setName(file.get(i).getOriginalFilename());
            image.setPath(imgPath);
            image.setType(file.get(i).getContentType());
            image.setSequence(cnt++);
            image.setPost(post);
            imageRepository.save(image);
        }
    }
}
