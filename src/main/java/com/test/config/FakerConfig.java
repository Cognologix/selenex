package com.test.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 *  this is faker config class , not used widely in automation framework.
 *  defined this class if r=in case of required ant fake test data.
 * */
@Configuration
public class FakerConfig {

    @Bean
    public Faker faker(){

        return  new Faker();
    }
}
