package dang.gun.com;

import dang.gun.com.jwt.BeerRequestFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DangGunBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(DangGunBackApplication.class, args);
    }


    @Bean
    public FilterRegistrationBean setFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new BeerRequestFilter());
        filterRegistrationBean.addUrlPatterns("/users/filtered/*");  //string 여러개를 가변인자로 받는 메서드
        return filterRegistrationBean;
    }
}
