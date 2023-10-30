package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class WebApplicationSPA {
    public static void main(String[] args) {
        SpringApplication.run(WebApplicationSPA.class,args);
    }
    @GetMapping("/spa")
    public String spa() {
        return "addressbookSPA";
    }
}
