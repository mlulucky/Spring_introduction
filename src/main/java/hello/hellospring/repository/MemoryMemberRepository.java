package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    // 회원을 저장할 메모리
    private static Map<Long, Member> store = new HashMap<>(); // <key , value> 키 - 값 쌍으로 데이터 저장
    // 자동으로 생성되는 key 값
    private static long sequence = 0L;

    public void clearStore() {
        store.clear(); // store 메모리를 비운다. // 테스트시 데이터를 비우는 용도로 사용예정
    }

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 반환값이 null 인 경우  Optional.ofNullable 감싸면 클라이언트에서 값을 처리할 수 있음.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() // 람다식 // store 에 저장된 값들을 전체 조회하면서 member 의 이름을 가져오는데, 값이같은지. 하나라도 있으면 요소를 반환
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store 에 저장된 회원정보 values 들을 전체를 반환
    }

}
