package com.udemy.spring.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ResponseMessage m201 = customMessage1();
    private final ResponseMessage m204put = simpleMessage(204, "Change ok");
    private final ResponseMessage m204del = simpleMessage(204, "Delete ok");
    private final ResponseMessage m403 = simpleMessage(403, "Non authorized");
    private final ResponseMessage m404 = simpleMessage(404, "Not found");
    private final ResponseMessage m422 = simpleMessage(422, "Validation Error");
    private final ResponseMessage m500 = simpleMessage(500, "Unexpected error");

    private ResponseMessage simpleMessage(int code, String msg) {
        return new ResponseMessageBuilder().code(code).message(msg).build();
    }


    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)

                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, Arrays.asList(m403, m404, m500))
                .globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m403, m422, m500))
                .globalResponseMessage(RequestMethod.PUT, Arrays.asList(m204put, m403, m404, m422, m500))
                .globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m204del, m403, m404, m500))

                .select()
                .apis(RequestHandlerSelectors.basePackage("com.udemy.spring.course.resources"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
        "Udemy Spring Course",
        "This Api is based on studies from course of Nelio Alves",
     "1.0 Version",
"https://www.udemy.com/terms",
            new Contact(
                "Matheus José",
                "https://www.github.com/enemydown1",
                "matheus.costa@tutanota.com"
            ),
    null, null, Collections.emptyList()
        );
    }

    private ResponseMessage customMessage1() {
        Map<String, Header> map = new HashMap<>();
        map.put("location", new Header("location", "New recourse URI", new ModelRef("string")));
        return new ResponseMessageBuilder()
                .code(201)
                .message("Created Recourse")
                .headersWithDescription(map)
                .build();
    }
}
