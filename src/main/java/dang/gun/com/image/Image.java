package dang.gun.com.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dang.gun.com.post.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String imgname;
    String imgpath;
    String imgtype;
    LocalDateTime created_at;
    LocalDateTime modified_at;
    LocalDateTime removed_at;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="post_id")
    Post post;

}
