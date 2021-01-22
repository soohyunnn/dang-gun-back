package dang.gun.com.post;

import dang.gun.com.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findOneById(int id);

    @Query(value = "select p.title, p.price, u.detailaddress, i.path, i.filename" +
            " from image i" +
            " inner join post p on i.post_id = p.id" +
            " inner join user u on u.id = p.user_id" +
            " where i.sequence = :sequence" +
            " order by p.id DESC" +
            " limit 8", nativeQuery = true)
    List<PostAllDto> findPostBySequenceLimit8Inner(@Param("sequence") int order);

}
