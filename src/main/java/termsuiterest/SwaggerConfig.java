package termsuiterest;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration of the Swagger generation.
 * @author Victor
 */
@Configuration
public class SwaggerConfig {

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("oeg.tagger.rest"))
//                .paths(PathSelectors.any())
//                .build()
//                .useDefaultResponseMessages(false)
//                .apiInfo(apiInfo());
//    }
    
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()//.addServersItem(new Server().url("https://annotador.oeg.fi.upm.es/"))
                .info(new Info().title("Annotador REST API")
                .description("TermSuite REST API Interface")
                .version("1.0")
                .license(new License().name("GPLv3").url("https://github.com/mnavasloro/Annotador/blob/master/LICENSE")))
                .externalDocs(new ExternalDocumentation()
                .description("Ontology Engineering Group - Universidad Politécnica de Madrid")
                .url(""));
    }

    /**
     * General information about the API
     */
//    private ApiInfo apiInfo() {
//        return new ApiInfo(
////                "UPM annotador REST API",
////                "UPM services for Temporal Tagging - annotador.",
//                "annotador REST API",
//                "Services for annotador.",
//                "1.0",
//                "http://futurelicense",
//                "Ontology Engineering Group - Universidad Politécnica de Madrid",
//                "No license yet",
//                "http://futurelicense");
//    }
}
