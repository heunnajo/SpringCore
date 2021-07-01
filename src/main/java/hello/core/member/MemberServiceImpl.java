package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    private MemberRepository memberRepository;//MemberRepository(인터페이스)만 존재! 인터페이스에만 의존한다!

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {//구현체에 뭐가 들어갈지는 생성자를 통해서 받는다!
        this.memberRepository = memberRepository;
    }

    public void join(Member member) {
        memberRepository.save(member);
    }
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
