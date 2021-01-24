package dang.gun.com.image;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dang.gun.com.post.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "image")
@Table(name = "image")
public class Image {


    /**
     * id : 이미지 고유 번호
     * filename : 파일 이름
     * path : 파일 경로
     * type : 파일 형식
     * created_at : 등록날짜
     * modified_at : 수정날짜
     * removed_at : 삭제날짜
     * post_id : 게시글 고유번호(외래키 , post)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "filename")
    String filename;
    String path;
    String type;
    @Column(name = "created_at")
    LocalDateTime createdAt = LocalDateTime.now();
    @Column(name = "modified_at")
    LocalDateTime modifiedAt;
    @Column(name = "removed_at")
    LocalDateTime removedAt;
    int sequence;

    @Column(name="post_id")
    int postId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="post_Id", referencedColumnName="id", insertable=false, updatable=false)
    Post post;

}
