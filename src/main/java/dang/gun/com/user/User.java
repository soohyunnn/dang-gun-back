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
@Table(name = "user")
public class User {

    /**
     * id : 회원 고유 번호
     * email : 이메일
     * username : 아이디
     * password : 비밀번호
     * addressnummber : 우편번호
     * detailaddress : 상세주소
     * prev_password : 이전 비밀번호
     * created_at : 가입날짜
     * modified_at : 수정날짜
     * removed_at : 탈퇴날짜
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(unique = true)
    String email;
    @Column(name="username", unique = true)
    String name;
    String password;
    @Column(name = "prev_password")
    String prevPassword;
    @Column(name = "addressnumber")
    String addressNumber;
    @Column(name = "detailaddress")
    String detailAddress;
    @Column(name = "created_at")
    LocalDateTime createdAt;
    @Column(name = "modified_at")
    LocalDateTime modifiedAt;
    @Column(name = "removed_at")
    LocalDateTime removedAt;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    public List<Post> posts = new ArrayList<Post>();

    public String getName() {
        return name;
    }
}
