package hello.hellospring.service;


import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    // MemberService memberService = new MemberService();
    // MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){ // 테스트 실행하기 전에 실행
        // 🌈 MemberService 객체를 테스트할때에 테스트에서 사용하는 객체와 서비스 객체가 동일한 객체를 사용하도록
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    // 회원가입 테스트
    @Test
    void 회원가입() {
        // given : 주어진 상황
        Member member =new Member();
        member.setName("spring");

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
        // 에러메시지 검증
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        System.out.println("e.getMessage() = " + e.getMessage());

//        try {
//            memberService.join(member2);
//        }catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }



        // then




    }

    @Test
    void findAll() {
    }

    @Test
    void findOne() {
    }
}