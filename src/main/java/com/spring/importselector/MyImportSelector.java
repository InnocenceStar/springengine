package com.spring.importselector;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//自定义逻辑，返回需要导入容器的组件
public class MyImportSelector implements ImportSelector {
    // 返回值：就是要导入到容器中的组件的全类名
    // AnnotationMetadata：当前标注@Import注解的类的所有注解信息，也就是说不仅能获取到@Import注解里面的信息，还能获取到其他注解的信息
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        //return new String[0];
        return new String[]{"com.spring.color.Blue", "com.spring.color.Yellow"};
    }
}
