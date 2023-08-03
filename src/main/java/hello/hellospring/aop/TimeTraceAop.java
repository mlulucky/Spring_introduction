package hello.hellospring.aop;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // 스프링 AOP 선언
public class TimeTraceAop {
    // ProceedingJoinPoint
    // @Around("execution(    )") : 🌈 공통 관심사를 어디에 적용할 것인가 -> 패키지, 클래스 ... 등

    // hello.hellospring 패키지 하위에 모두 적용 // 메서드 호출시 인터셉터로 실행되는 형식
    // 👀 TimeTraceAop 가 AOP 로 공통관심사 적용을 실행시키는데, 그 대상에 SpringConfig 에 메서드도 실행이 될때
    // SpringConfig 에 @Bean 등록된 👀 TimeTraceAop 자신도 공통관심사가 적용이 되는 -> 순환참조 에러발생 -> // SpringConfig 에 등록된 스프링 빈은 공통관심사 적용에서 제외
    @Around("execution(* hello.hellospring..*(..)) && !target(hello.hellospring.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable { // 예외 발생시 메서드 종료
        long start = System.currentTimeMillis();
        System.out.println("START = " + joinPoint.toString()); // joinPoint.toString() : 어떤 메서드를 실행하는지 알 수 있다.

        try {
            return joinPoint.proceed(); // joinPoint.proceed() : 다음 메서드로 진행 // 프록시 메서드가 아니라, 실제 메서드를 불러온다.
        } finally {
          long finish = System.currentTimeMillis();
          long timeMs = finish - start;
          System.out.println("END = " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }

}

// 문제
// 예) 공통 관심 사항 - 메서드 실행 시간을 측정하는 기능 / 핵심 관심사항 - 회원가입, 회원조회
// 공통 관심 사항과 핵심 비즈니스 로직이 섞이면 유지보수가 어렵다.
// 공통 관심 사항을 변경할때 모든 로직을 찾아가면서 변경해야 한다.

// AOP
// 공통 관심 사항 vs 핵심 관심 사항을 분리
// 원하는 곳에 공통 관심사항을 적용

// 해결
// 회원가입, 회원 조회등 핵심 관심사항과 시간을 측정하는 공통 관심 사항을 분리한다.
// 시간을 측정하는 로직을 별도의 공통 로직으로 만들었다.
// 핵심 관심 사항을 깔끔하게 유지할 수 있다.
// 변경이 필요하면 이 로직만 변경하면 된다.
// 원하는 적용 대상을 선택할 수 있다.