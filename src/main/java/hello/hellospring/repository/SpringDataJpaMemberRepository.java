package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // 인터페이스가 인터페이스를 상속받는 경우 extends // 클래스가 인터페이스를 상속받는 경우 implements
    // 🌈 JpaRepository 를 상속 받으면 -> 스프링데이터 jpa 가 interface 의 구현체를 직접 만들어서 스프링 빈으로 등록을 해준다.
    // 🌈 JpaRepository 를 상속 받는 걸로 인터페이스 만으로도 sql, orm 끝 (내가 따로 직접 구현해야할 것이 없다)
    @Override
    Optional<Member> findByName(String name); // JPQL select m from Member m where m.name = ?
    // findName 은 기본적은 메서드가 아니므로, 따로 override 목록에 추가를 했고 findAll 등의 기본 메서드는 스프링 데이터 Jpa 가 만들어줌
}
