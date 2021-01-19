package dang.gun.com.image;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

//    @Query("select i from image i left outer join post p on p.id=i.post_id where order_number = '1'")
//    List<Image> findByOrderNumber();


}
