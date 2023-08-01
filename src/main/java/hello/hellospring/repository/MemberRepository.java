package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 회원 객체를 저장하는 저장소
    // 기능 - 회원저장, 회원조회
    Member save(Member member);
    Optional<Member> findById(Long id); // Optional : java8 기능. id 가 없으면 null 인데
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
