package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 스프링 어노테이션은 스프링이 관리해준다.
// 스프링부트에서 👀처음 스프링컨테이너가 뜰때
// @Controller 어노테이션이 있으면
// MemberController 클래스 객체를 생성해서 👀스프링컨테이너에 넣어둔다(등록한다). 그리고 🌈 스프링(스프링 컨테이너)이 관리를 한다!
@Controller
public class MemberController {
    private final MemberService memberService;
    // DI 필드로 객체 주입
    // @Autowired private MemberService memberService;

    // 스프링 컨테이너가 Controller 객체를 호출할때, @Autowired 생성자도 같이 호출한다.
    // @Autowired : 스프링이 스프링컨테이너에 있는 MemberService 를 연결 해준다.

// DI 생성자로 객체 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
