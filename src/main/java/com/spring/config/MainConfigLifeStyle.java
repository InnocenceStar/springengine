package com.spring.config;

import com.spring.bean.Car;
import com.spring.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@ComponentScan("com.spring.bean")
@Configuration
public class MainConfigLifeStyle {


    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }

    @Scope("prototype")
    @Bean(initMethod = "afterPropertiesSet",destroyMethod = "destroy")
    public Cat cat(){
        return new Cat();
    }

}
