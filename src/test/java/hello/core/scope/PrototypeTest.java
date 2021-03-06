package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {
    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        //prototypeBean은 조회 직전에 생성된다!
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = "+prototypeBean1);
        System.out.println("prototypeBean2 = "+prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        //닫아야한다면 수동으로 직접 닫아야한다.
        prototypeBean1.destroy();
        prototypeBean2.destroy();
        ac.close();
    }
    @Scope("prototype")
    //@Component 없어도 AnnotationConfigApplicationContext에 넣어주면 컴포넌트 스캔의 대상처럼 동작!
    static class PrototypeBean {
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
