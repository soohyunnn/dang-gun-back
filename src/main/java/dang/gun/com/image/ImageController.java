package dang.gun.com.image;

import org.springframework.stereotype.Controller;

@Controller
public class ImageController {

    private  final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    public void save(Image image){
        imageService.save(image);
    }
}
