package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
    // @Autowired
    // SpringConfig 에서 빈 등록해놓았음
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 화면
    @GetMapping("/members/new") // http Get 방식 : url 로 데이터를 전달할때, 보통 화면에서 데이터를 조회할 때
    public String createForm() {
        return "members/createMemberForm";
    }

    // 회원 등록
    @PostMapping("/members/new") // http Post 방식 : 보통 form 태그(method="post") 로 데이터를 전달할때, 보통 데이터를 등록할 때
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName()); // MemberForm 의 name 에 createMemberForm 화면 input 태그의 name="name" 에 입력한 값이 들어온다. ( 스프링이 직접 넣어준다. )
        memberService.join(member);

        System.out.println("member.getName() = " + member.getName());
        
        return "redirect:/";
    }

   @GetMapping("/members")
   public String list(Model model) {
        List<Member> members = memberService.findAll();
        model.addAttribute("members", members);
        return "/members/memberList.html";
   }


}
