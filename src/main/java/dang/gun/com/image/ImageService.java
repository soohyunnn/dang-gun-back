package dang.gun.com.image;

import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public int save(Image image) {
        imageRepository.save(image);
        return image.getId();
    }
}
