package com.xiezhenyu;

import com.xiezhenyu.listener.MyApplicationListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Tim
 * @date 2020/12/24
 */
@SpringBootApplication
public class YxkBbsAdminApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(YxkBbsAdminApplication.class);
        springApplication.addListeners(new MyApplicationListener());
        springApplication.run(args);
    }

}
