package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {
    @Test
    void AutowiredOption(){
        //ComponentScan처럼 TestBean을 빈 등록해준다!
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }
    static class TestBean {
        //스프링에 빈이 없는 경우(스프링 컨테이너에 등록되지 않은 일반 클래스 Member)
        //1. 메서드 자체가 호출되지 않는다!
        @Autowired(required = false)//required의 기본값은 true.
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }
        //2. 호출은 되지만, null이 들어간다!
        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }
        //3. 자바8에서 제공하는 Optional : 빈이 없으면 Optional.empty를 넣는다!
        //Optional 안에 값이 감싸져있다고 생각!
        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }
    }
}
