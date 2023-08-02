package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    // 🌈 application.properties 파일에 jpa 설정을 넣어주면 스프링부트가 EntityManager 를 만들어줘 사용할 수 있다.
    private final EntityManager em;  // jpa entity 를 사용하려면 EntityManager 를 주입받아야 한다.

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id); // pk 로 쿼리를 실행하는 경우
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) { // pk 가 아닌 경우에는 jpql 쿼리문을 작성해줘야 한다.
        List<Member> result =  em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
