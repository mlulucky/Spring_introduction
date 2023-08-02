package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest // 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional // 🌈 테스트를 실행할때 Transactional 을 먼저 실행 -> 테스트가 끝나면 db 를 rollback 을 해준다. (테스트시 반영됬던 db 데이터들이 반영이 안되고 다 지워진다 - db 초기화)
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;


    // 회원가입 테스트
    @Test
    void 회원가입() {
        // given : 주어진 상황
        Member member =new Member();
        member.setName("spring 공부중2");

        // when : 실행 시 (검증)
        Long memberId = memberService.join(member);

        // then : 결과
        Member findMember = memberService.findOne(memberId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 🌈 테스트는 예외처리. 에러 검증테스트도 중요하다! 에러가 정상적으로 작동하는지도 테스트!

    @Test
    void 중복회원_예외(){ // 회원 이름 중복불가
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// assertThrows(예외발생, 로직실행) : 로직 실행시, 예외발생해야 테스트 성공!

        // then // 에러메시지 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        System.out.println("e.getMessage() = " + e.getMessage());

    }

}