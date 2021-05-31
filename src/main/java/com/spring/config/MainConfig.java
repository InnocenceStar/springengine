package com.spring.config;

import com.spring.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Configuration
@ComponentScan(value="com.spring", includeFilters={
        /*
         * type：指定你要排除的规则，是按照注解进行排除，还是按照给定的类型进行排除，还是按照正则表达式进行排除，等等
         * classes：我们需要Spring在扫描时，只包含@Controller注解标注的类
         */
        @ComponentScan.Filter(type= FilterType.ANNOTATION, classes={Controller.class})
}, useDefaultFilters=false)
@ComponentScan(value="com.spring", includeFilters={
        /*
         * type：指定你要排除的规则，是按照注解进行排除，还是按照给定的类型进行排除，还是按照正则表达式进行排除，等等
         * classes：我们需要Spring在扫描时，只包含@Service注解标注的类
         */
        @ComponentScan.Filter(type=FilterType.ANNOTATION, classes={Service.class})
}, useDefaultFilters=false) // value指定要扫描的包
public class MainConfig {
    // @Bean注解是给IOC容器中注册一个bean，类型自然就是返回值的类型，id默认是用方法名作为id
    @Bean
    public Person person() {
        return new Person("liayun", 20);
    }
}
