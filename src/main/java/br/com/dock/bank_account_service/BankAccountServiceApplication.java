package br.com.dock.bank_account_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class BankAccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(br.com.dock.bank_account_service.BankAccountServiceApplication.class, args);
    }

    @Bean
    public Docket bankAccountApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .host("localhost:8090")
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Bank Account Service",
                "Information about API",
                "API V1.0",
                "Terms of service",
                new Contact("Anderson Silva", "www.dock.bank-account-service.com.br", "anderson.silva@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}




