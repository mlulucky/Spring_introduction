package hello.hellospring;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바 코드로 직접 스프링 빈 등록하기 (@Service, @Repository, @Autowired 사용 안하고)
// 구현체를 변경해야할 경우에 어노테이션을 직접 수정하지 않고 설정파일 🌈 SpringConfig 만 수정해도 되는 장점!! 이 있다.
@Configuration // 스프링이 @Configuration 을 읽고 @Bean 으로 등록된 객체를 스프링 어노테이션으로 등록한다. // 스프링 어노테이션이 아니라 직접 스프링 빈 등록
public class SpringConfig {

    @Bean // 스프링 빈 등록 -> 스프링 빈 컨테이너에 올려준다.
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


}
