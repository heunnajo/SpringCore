package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletonTest {
    //수많은 클라이언트의 요청에 대해 객체를 무수히 생성하면 어떻게 될까?
    //JVM 메모리에 계속 객체가 생성되어 올라간다!
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        //1.조회 : 호출할 때마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();
        //2.참조값이 다른 것을 확인
//        System.out.println("memberService1 = " + memberService1);
//        System.out.println("memberService2 = " + memberService2);
        //memberService1 != memberService2 테스트자동화!
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }


}
