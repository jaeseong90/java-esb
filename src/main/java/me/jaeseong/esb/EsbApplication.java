package me.jaeseong.esb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "classpath:spring/camel-context.xml" })
public class EsbApplication {

    public static void main(String[] args) {

        SpringApplication.run(EsbApplication.class, args);
    }

}
