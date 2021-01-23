package dang.gun.com.image;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> getByPost_id(int id);
}
