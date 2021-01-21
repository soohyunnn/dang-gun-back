package dang.gun.com.image;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;


@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "select * from image i inner join post p on i.post_id = p.id where i.order_number = :order order by p.id DESC limit 8", nativeQuery = true)
    List<Image> findImageQueryNative(@Param("order") int order);

    // @Query(value = "select * from image i inner join post p where i.order_number = ?1 order by p.id DESC limit 2", nativeQuery = true)
    //@Query(value = "select i from image i inner join i.post p where i.orderNumber = ?1 order by p.id DESC")

//    public static void Query(EntityManager em) {
//        String jpql = "SELECT i.id from image i";
//        Query query = em.createQuery(jpql);
//
//        List list = query.getResultList();
//
//        System.out.println("list = " + list);
//    }


}
