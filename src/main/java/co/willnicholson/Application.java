package co.willnicholson;


import co.willnicholson.DTOs.FightDTO;
import co.willnicholson.DTOs.FightEntity;
import co.willnicholson.DTOs.FighterEntity;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@EnableScheduling
@SpringBootApplication
@EnableSwagger2
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(Application.class);
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

    @Bean
    public MapperFacade mapperFactory(){
        MapperFactory mappperFactory = new DefaultMapperFactory.Builder().build();
        mappperFactory.classMap(FightEntity.class, FightDTO.class);
        mappperFactory.classMap(FighterEntity.class, FightDTO.class);
        return mappperFactory.getMapperFacade();
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }



}
