package dang.gun.com.image;

import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image saveImage(Image image) {

        Image imageResponse = imageRepository.save(image);
        return imageResponse;
    }
}
