package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA",BeanA.class);
        assertThat(beanA).isNotNull();

        //컴포넌트 스캔대상에서 빠자기 때문에 아래 에러가 터져야한다!
        assertThrows(
                NoSuchBeanDefinitionException.class,
                () ->ac.getBean("beanB",BeanB.class));
    }

    @Configuration
    @ComponentScan(//type = FilterType.ANNOTATION, 지워도 잘 동작한다!
            includeFilters = @Filter(classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )

    static class ComponentFilterAppConfig{
        /*OrderServiceImpl에 들어가면 생성자 위에 @Autowired 있다.
         구현 클래스 위에 @Component를 스캔하면 OrderServiceImpl가 스프링 빈에 등록될 때 생성자를 호출한다!
         생성자 호출할 때 Autowired를 인지하고 스프링 컨테이너에서 MemberRepository 빈과 DiscountPolicy 빈을 꺼내서 주입해준다.
         */

    }
}
