package hello.core;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //이제 AppConfig로 MemberService 를 만든다!
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();//이안에는 MemberServiceImpl이 들어가있다!

        //스프링은 AppConfig의 설정 정보를 가지고 @Bean 붙은 것들을 컨테이너에 객체 생성해서 관리한다!
        //이제 컨테이너를 통해서 객체를 가져와야한다!(이름,타입)을 알려주면 스프링 컨테이너에서 찾아서 반환해준다!
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService",MemberService.class);//(이름,타입)

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    } }