package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public class MemberService { // ctrl + shift + T : 테스트파일 생성
    // 회원서비스 -> repository 필요
    // 🌈 MemberService 객체를 테스트할때에 테스트에서 다시 객체를 생성하여 사용하므로, 서비스 객체와 테스트 서비스객체가 서로다름
    // 굳이 같은 객체인데 새로만들 필요가 있을까 ? -> 생성자 주입으로 통한 객체 생성하기!
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 🌈 MemberService 객체를 테스트할때에 테스트에서 사용하는 객체와 서비스 객체가 동일한 객체를 사용하도록
    // -> Service 에서 객체를 생성하는 것이 아니라 생성자에 객체를 주입받아서 사용하도록 바꾼 것!
    private final MemoryMemberRepository memberRepository;
    // MemberService 생성자

    public MemberService(MemoryMemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     */
    public Long join(Member member) {
        // 같은 이름의 중복 회원 X
        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // Optional<Member> result = memberRepository.findByName(member.getName()); // ctrl + alt + v => 자동생성
        memberRepository.findByName(member.getName()) // findByName 는 메서드 -> join 회원가입 메서드 바깥으로 따로 메서드로 빼는 게 좋다. // shift + ctrl + alt + T
                .ifPresent( mem -> { throw new IllegalStateException("이미 존재하는 회원입니다.");}); // .ifPresent : null 이 아니라 값이 있으면 // mem == member
    }

    // 서비스는 비즈니스 로직에 가깝게 // repository 는 단순히 데이터를 저장소에 넣고 빼는 용도로

    /**
     * 전체 회원 조회
     */
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    /**
     * 회원 1명 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
