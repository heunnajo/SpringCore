package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long,Member> store = new HashMap<>();//동시성 이슈 때문에 ConcurrentHashMap을 써야한다!(여러군데에서 동시에  접근하는 경우)
    @Override
    public void save(Member member) {
        store.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
