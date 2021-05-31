package com.spring.custom;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * 自定义本地线程级别的bean作用域，不同的线程中的bean是不同的实例，同一个线程中同名的bean是同一个实例
 * @author liayun
 *
 */
public class ThreadScope implements Scope {

    public static final String THREAD_SCOPE = "thread";

    private ThreadLocal<Map<String, Object>> beanMap = new ThreadLocal() {

        @Override
        protected Object initialValue() {
            return new HashMap<>();
        }

    };

    /**
     * 返回当前作用域中name对应的bean对象
     * @param name：需要检索的bean对象的名称
     * @param objectFactory：如果name对应的bean对象在当前作用域中没有找到，那么可以调用这个objectFactory来创建这个bean对象
     */
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object bean = beanMap.get().get(name);
        if (Objects.isNull(bean)) {
            bean = objectFactory.getObject();
            beanMap.get().put(name, bean);
        }
        return bean;
    }

    /**
     * 将name对应的bean对象从当前作用域中移除
     */
    @Override
    public Object remove(String name) {
        return this.beanMap.get().remove(name);
    }

    /**
     * 用于注册销毁回调，若想要销毁相应的对象，则由Spring容器注册相应的销毁回调，而由自定义作用域选择是不是要销毁相应的对象
     */
    // bean作用域范围结束的时候调用的方法，用于bean的清理
    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        System.out.println(name);
    }

    /**
     * 用于解析相应的上下文数据，比如request作用域将返回request中的属性
     */
    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    /**
     * 作用域的会话标识，比如session作用域的会话标识是sessionId
     */
    @Override
    public String getConversationId() {
        return Thread.currentThread().getName();
    }

}
