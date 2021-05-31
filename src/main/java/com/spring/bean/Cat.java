package com.spring.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Cat implements InitializingBean, DisposableBean {

    public Cat() {
        System.out.println("cat constructor...");
    }

    /**
     * 会在容器关闭的时候进行调用
     */
    @Override
    public void destroy() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("cat destroy...");
    }

    /**
     * 会在bean创建完成，并且属性都赋好值以后进行调用
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("cat afterPropertiesSet...");
    }
}
