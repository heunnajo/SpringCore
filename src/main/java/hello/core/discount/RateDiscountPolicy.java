package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent = 10;//10% 할인
    @Override
    public int discount(Member member, int price) {
        //enum타입은 == 쓰는 게 맞다.
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;// = 할인되는 금액 = price * 10/100
        } else{
            return 0;
        }
    }
}
