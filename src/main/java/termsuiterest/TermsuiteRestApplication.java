package termsuiterest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TermsuiteRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TermsuiteRestApplication.class, args);
    }

//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(TermsuiteRestApplication.class);
//    }
}
