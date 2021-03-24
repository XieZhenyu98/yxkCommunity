package com.xiezhenyu;

import com.xiezhenyu.listener.MyApplicationListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Tim
 */
@SpringBootApplication
public class YxkBbsApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(YxkBbsApplication.class);
        springApplication.addListeners(new MyApplicationListener());
        springApplication.run(args);
    }

}
