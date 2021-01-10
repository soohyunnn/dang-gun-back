package dang.gun.com.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dang.gun.com.post.Post;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="user")
public class User {

    /**
     * id : 회원 고유 번호
     * email : 이메일
     * username : 아디이
     * password : 비밀번호
     * prev_password : 이전 비밀번호
     * created_at : 가입날짜
     * modified_at : 수정날짜
     * removed_at : 탈퇴날짜
     */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String email;
    String username;
    String password;
    String prev_password;
    String address;
    LocalDateTime created_at;
    LocalDateTime modified_at;
    LocalDateTime removed_at;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts = new ArrayList<Post>();
}
