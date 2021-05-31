package com.spring.config;

import com.spring.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@Configuration
public class MainConfig2 {

    @Scope("prototype") // 通过@Scope注解来指定该bean的作用范围，也可以说成是调整作用域
    @Bean("person22")
    public Person person2() {
        return new Person("per2", 25);
    }
    @Bean("person11")
    public Person person1() {
        System.out.println("person11: not lazy load");
        return new Person("per1", 25);
    }
    //test 懒加载：在使用时才会加在，而不是在IOC创建时就加载
    @Lazy
    @Bean("person33")
    public Person person3() {
        System.out.println("person33: lazy load");
        return new Person("per3", 25);
    }
}
