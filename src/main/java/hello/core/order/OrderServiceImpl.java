package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{
    //인터페이만 존재!DIP 만족!
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
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
}
