package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
// 테스트 실행은 순서보장이 안된다. -> 테스트 실행 순서로 데이터가 의존되버리면 테스트 에러발생
// 🌈 테스트 실행은 테스트 순서에 의존 되면 안된다 -> 테스트 실행이 끝날때마다 repository 저장소,공용데이터를 새로 비워주기 -> store.clear()
    @AfterEach // 테스트 메서드가 끝날때마다 실행
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();
        // Assertions.assertEquals(member, result);
        // Assertions.assertThat(member).isEqualTo(result);
        System.out.println("result " + result);
        assertThat(member).isEqualTo(result); // alt + enter -> import Assertions
    }

    @Test
    public void findName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring2").get(); // get() : Optional 을 한번 까서 값을 가져온다.
        System.out.println("result " + result);
        assertThat(member2).isEqualTo(result);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }


}
