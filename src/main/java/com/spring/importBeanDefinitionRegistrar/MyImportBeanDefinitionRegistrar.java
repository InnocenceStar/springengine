package com.spring.importBeanDefinitionRegistrar;

import com.spring.color.Orange;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * AnnotationMetadata：当前类的注解信息
     * BeanDefinitionRegistry：BeanDefinition注册类
     *
     * 我们可以通过调用BeanDefinitionRegistry接口中的registerBeanDefinition方法，手动注册所有需要添加到容器中的bean
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        boolean b1 = beanDefinitionRegistry.containsBeanDefinition("com.spring.color.Blue");
        boolean b2 = beanDefinitionRegistry.containsBeanDefinition("com.spring.color.Red");
        if (b1 && b2) {
            // 指定bean的定义信息，包括bean的类型、作用域等等
            // RootBeanDefinition是BeanDefinition接口的一个实现类
            RootBeanDefinition beanDefinition = new RootBeanDefinition(Orange.class); // bean的定义信息
            // 注册一个bean，并且指定bean的名称
            beanDefinitionRegistry.registerBeanDefinition("orange", beanDefinition);
        }
    }
}
