package com.spring.config;

import com.spring.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MainConfig3 {

    @Scope("thread")
    @Bean("person")
    public Person person() {
        System.out.println("给容器中添加咱们这个Person对象...");
        return new Person("美美侠", 25);
    }
}
