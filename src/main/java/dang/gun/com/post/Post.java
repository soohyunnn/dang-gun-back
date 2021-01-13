package dang.gun.com.post;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dang.gun.com.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="post")
public class Post {

    /**
     * id : 게시글 고유 번호
     * title : 제목
     * content : 내용
     * price : 금액
     * view_cnt : 조회 수
     * like_cnt : 좋아요 수
     * created_at : 등록날짜
     * modified_at : 수정날짜
     * removed_at : 삭제날짜
     * user_id : 회원관리번호(외래키, user)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    String content;
    int price;
    int view_cnt;
    int like_cnt;
    LocalDateTime created_at;
    LocalDateTime modified_at;
    LocalDateTime removed_at;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;


}
