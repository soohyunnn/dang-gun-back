package dang.gun.com.image;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ImageServiceTest {

    @Autowired
    private ImageRepository imageRepository;

//    @Test
//    public void test() {
//        List<Image> imageList = imageRepository.findImageQueryNative();
//        System.out.println("imageList = " + imageList);
//    }

    @Test
    public static void Query(@NotNull EntityManager em) {
        String jpql = "SELECT i.id from image i";
        Query query = em.createQuery(jpql);

        List list = query.getResultList();

        System.out.println("list = " + list);
    }


}