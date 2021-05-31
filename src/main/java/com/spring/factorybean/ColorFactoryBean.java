package com.spring.factorybean;

import com.spring.color.Color;
import org.springframework.beans.factory.FactoryBean;

public class ColorFactoryBean implements FactoryBean<Color> {
    //返回一个对象，并添加在容器中
    @Override
    public Color getObject() throws Exception {
        System.out.println("ColorFactoryBean...getObject...");
        return new Color();
    }
    //获取对象的类型
    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }
    //判断是否为单实例
    @Override
    public boolean isSingleton() {
        //设置为单实例时（返回true时），获得的对象是同一个
        //false，获取的对象则不是同一个
        return true;
    }
}
