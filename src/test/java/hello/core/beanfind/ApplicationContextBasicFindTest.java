package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {
    //인터페이스
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
//        System.out.println("memberService = " + memberService);
//        System.out.println("memberService.getClass() = " + memberService.getClass());
    }
    @Test
    @DisplayName("이름없이 타입으로만 조회")
    void findByType(){//인터페이스로 조회한다!
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        //역할과 구현 분리하고, 역할(인터페이스)에 의존해야한다. 고로 아래 구체에 접근하는것은 좋지 않다.
        //구체타입으로 조회하면 유연성 떨어진다!
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        //빈 이름으로 조회했는데 없다면?
//        MemberService xxxx = ac.getBean("XXXX", MemberService.class);
        //Java8의 람다 기능을 써서 검증한다!
        //이 예외가 터져야 테스트가 성공한다!(assertThrows가 던져지면 테스트 성공!)
        assertThrows(NoSuchBeanDefinitionException.class,//콤마 왼쪽 : 이게 터져야한다!
                () -> ac.getBean("XXXX",MemberService.class));//콤마 오른쪽 : 이 로직이 실행되면
    }
}
