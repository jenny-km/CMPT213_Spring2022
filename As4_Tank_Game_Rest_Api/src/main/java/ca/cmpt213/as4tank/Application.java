package ca.cmpt213.as4tank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * As4 - Tank Game Rest Api by Jennifer Kim
 *
 * Application Class
 * - initializes the Spring boot to be used and connects to the application
 */

@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
