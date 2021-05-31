import com.spring.bean.Person;
import com.spring.config.MainConfig;
import com.spring.config.MainConfig2;
import com.spring.config.MainConfig3;
import com.spring.config.MainConfig4;
import com.spring.custom.ThreadScope;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

public class IOCTest {
    @Test
    public void testXML() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        // 我们现在就来看一下IOC容器中有哪些bean，即容器中所有bean定义的名字
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }
    @Test
    public void testAnnotation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        // 我们现在就来看一下IOC容器中有哪些bean，即容器中所有bean定义的名字
        String[] definitionNames = context.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
    }
    @Test
    public void testAnnotation2() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

        // 获取到的这个Person对象默认是单实例的，因为在IOC容器中给我们加的这些组件默认都是单实例的，
        // 所以说在这儿我们无论多少次获取，获取到的都是我们之前new的那个实例对象
        Person person = (Person) applicationContext.getBean("person11");
        Person person2 = (Person) applicationContext.getBean("person11");
        System.out.println("单实例为(对象在Spring容器中默认是单实例的)："+(person == person2));

        Person person221 = (Person) applicationContext.getBean("person22");
        Person person222 = (Person) applicationContext.getBean("person22");
        System.out.println("多实例为(@Scope(\"prototype\"))："+(person221 == person222));
    }
    @Test
    public void testScope() {
        AnnotationConfigApplicationContext applicationContext;
        applicationContext = new AnnotationConfigApplicationContext(MainConfig3.class);
        // 向容器中注册自定义的Scope
        applicationContext.getBeanFactory().registerScope(ThreadScope.THREAD_SCOPE, new ThreadScope());

        // 使用容器获取bean
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                //为了看出区别，在这里可以先将Person的toString方法注释掉；
                System.out.println(Thread.currentThread() + "," + applicationContext.getBean("person"));
                System.out.println(Thread.currentThread() + "," + applicationContext.getBean("person"));
            }).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testLazy() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);
        Person person11 =(Person) applicationContext.getBean("person11");
        //测试懒加载
        System.out.println("IOC容器创建完成");
        Person person = (Person) applicationContext.getBean("person33");
        Person person2 = (Person) applicationContext.getBean("person33");
        System.out.println(person == person2);
    }

    @Test
    public void testConditional(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig4.class);
        String[] beanNamesForType = context.getBeanNamesForType(Person.class);
        for(String name:beanNamesForType){
            System.out.println(name);
        }
    }

    @Test
    public void testImport() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig4.class);
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }
        //获取color对象
        Object colorFactoryBean = applicationContext.getBean("colorFactoryBean");
        System.out.println("bean的类型：" + colorFactoryBean.getClass());
        //获取color工厂对象本身
        Object colorFactoryBean1 = applicationContext.getBean("&colorFactoryBean");
        System.out.println("bean的类型：" + colorFactoryBean1.getClass());
    }


}
