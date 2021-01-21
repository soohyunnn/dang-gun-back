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


    public void save(List<MultipartFile> file, int postId) throws IOException {
//        int cnt = 1;
//        for (int i = 0; i < file.size(); i++) {
//            Image image = new Image();
//            String imgPath = s3Uploader.upload(file.get(i));
//
//            Post post = new Post();
//            post.setId(postId);
//
//            image.setName(file.get(i).getOriginalFilename());
//            image.setPath(imgPath);
//            image.setType(file.get(i).getContentType());
//            image.setOrderNumber(cnt++);
//            image.setPost(post);
//            imageRepository.save(image);
//        }

        List<Image> imageList = imageRepository.findImageQueryNative(1);

        for(int i = 0 ; i < imageList.size(); i++){
            System.out.println("imageList = " + imageList.get(i).getName()+ imageList.get(i).getPost().getId());
        }

    }
}
