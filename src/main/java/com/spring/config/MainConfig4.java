package com.spring.config;

import com.spring.bean.Person;
import com.spring.color.Color;
import com.spring.color.Red;
import com.spring.condition.LinuxCondition;
import com.spring.condition.WindowsCondition;
import com.spring.factorybean.ColorFactoryBean;
import com.spring.importBeanDefinitionRegistrar.MyImportBeanDefinitionRegistrar;
import com.spring.importselector.MyImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class}) // @Import快速地导入组件，id默认是组件的全类名
@Configuration
public class MainConfig4 {

    @Conditional(WindowsCondition.class)
    @Bean("win")
    public Person perWin(){
        return new Person("win", 11);
    }
    @Conditional(LinuxCondition.class)
    @Bean("linux")
    public Person perLinux(){
        return new Person("linux", 11);
    }

    @Bean
    public ColorFactoryBean colorFactoryBean(){
        return new ColorFactoryBean();
    }
}
