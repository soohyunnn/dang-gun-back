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

    /**
     * AWS s3에 이미지 저장
     * 1. AWS s3에 저장 후 저장된 url 반환
     * 2. 반환된 url과 이미지에 관한 정보를 DB에 저장
     * @param fileList
     * @param postId
     * @throws IOException
     */
    public void save(List<MultipartFile> fileList, int postId) throws IOException {
        int cnt = 1;
        for (int i = 0; i < fileList.size(); i++) {
            Image image = new Image();
            String imgPath = s3Uploader.upload(fileList.get(i));

            Post post = new Post();
            post.setId(postId);

            image.setFilename(fileList.get(i).getOriginalFilename());
            image.setPath(imgPath);
            image.setType(fileList.get(i).getContentType());
            image.setSequence(cnt++);
            image.setPost(post);
            imageRepository.save(image);
        }
    }

    /**
     * 게시글 상세 이미지 슬라이드 목록 조회
     * @param postId
     * @return
     */
    public List<Image> findAllByPostId(int postId) {
        List<Image> imageList = imageRepository.getByPostId(postId);
        return imageList;
    }
}
