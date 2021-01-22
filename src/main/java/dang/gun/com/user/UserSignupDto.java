package dang.gun.com.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data//게터 세터 자동으로 만들어준다.
@NoArgsConstructor//기본 생성자
@AllArgsConstructor//모든 매개변수를 가지고 있는 생성자
@Builder//생성자 쉽게 만들어주는 기능
public class UserSignupDto {
    String email;
    String username;
    String password;
    String addressNumber;
    String detailAddress;
}
