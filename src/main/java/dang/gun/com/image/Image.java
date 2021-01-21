package dang.gun.com.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dang.gun.com.post.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name="image")
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "imgname")
    String name;
    @Column(name = "imgpath")
    String path;
    @Column(name = "imgtype")
    String type;
    @Column(name = "created_at")
    LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "modifiedAt")
    LocalDateTime modified_at;
    @Column(name = "removedAt")
    LocalDateTime removed_at;
    @Column(name = "order_number")
    int orderNumber;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;

    public Post getPost() {
        return post;
    }
}
