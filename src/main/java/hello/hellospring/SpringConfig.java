package hello.hellospring;

import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직접 스프링 빈 등록하기 (@Service, @Repository, @Autowired 사용 안하고)
// 구현체를 변경해야할 경우에 어노테이션을 직접 수정하지 않고 설정파일 🌈 SpringConfig 만 수정해도 되는 장점!! 이 있다.
@Configuration // 스프링이 @Configuration 을 읽고 @Bean 으로 등록된 객체를 스프링 어노테이션으로 등록한다. // 스프링 @Component 어노테이션(@Controller, @Repository, @Service)이 아니라 직접 스프링 빈 등록
public class SpringConfig {

    // 🍕 스프링 데이터 JPA가 -> JpaRepository 를 상속받은 SpringDataJpaMemberRepository 를 스프링 빈으로 자동 등록해준다. // 스프링 데이터 JPA 회원 리포지토리를 사용하도록 스프링 설정 변경
    // MemberRepository 를 직접 구현하여 스프링 빈으로 등록시킴
    private final MemberRepository memberRepository; // 👀 필드의 초기화(선언) ->  생성자를 통해서

    @Autowired // 🍕 스프링 빈으로 등록된 객체 memberRepository 를 의존성 주입해줌. // 생성자가 1개라서 생략해도 됨
    public SpringConfig(MemberRepository memberRepository) { // 어디서 스프링 빈으로 등록이 된건가 -> (JpaRepository 를 상속받은 경우) SpringDataJpa 가 스프링 빈으로 등록시켜줌 // SpringDataJpaMemberRepository.java 에서
        this.memberRepository = memberRepository; // 👀
    }

    @Bean // 스프링 빈 등록 -> 스프링 빈 컨테이너에 올려준다.
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        // return new MemoryMemberRepository(); // memory(해시맵) db
//        // return new JdbcMemberRepository(dataSource); // j2 database db
//        // return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em); // jpa orm 사용시 EntityManage 필요
//    }

}
