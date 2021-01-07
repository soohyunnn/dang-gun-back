package dang.gun.com;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="test")
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name_u;


   public Test(int i, String name_u) {
       this.id= id;
       this.name_u = name_u;
    }
}
