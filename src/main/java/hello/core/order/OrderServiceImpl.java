package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    //인터페이만 존재!DIP 만족!
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    //@Autowired 생성자 딱 1개니까 생략가능!

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy  DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //회원을 먼저 찾고
        Member member = memberRepository.findById(memberId);
        //단일책임원칙을 잘 지키며 할인에 대한 역할은 discountPolicy에 넘긴다!
        int discountPrice = discountPolicy.discount(member,itemPrice);
        return new Order(memberId,itemName,itemPrice,discountPrice);//주문결과 반환.
    }
    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
