package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        //스프링 컨테이너 생성~
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //statefulService1,2는 같은 인스턴스인데 값을 넣어버리니까 값을 덮어씐다!
        //ThreadA : A사용자 10000원 주문
        statefulService1.order("userA",10000);
        //ThreadB : B사용자 20000원 주문
        statefulService2.order("userB",20000);
        
        //ThreadA: 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);
        //statefulService1의 금액이 20000원이냐?
        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }
    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
